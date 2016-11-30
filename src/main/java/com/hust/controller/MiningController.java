package com.hust.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.hust.constants.Constant;
import com.hust.constants.Constant.Index;
import com.hust.model.IssueWithBLOBs;
import com.hust.model.params.StatisticParams;
import com.hust.service.ClusterService;
import com.hust.service.IssueService;
import com.hust.service.StatisticService;
import com.hust.util.ConvertUtil;
import com.hust.util.ResultUtil;

@Controller
@RequestMapping("/mining")
public class MiningController {
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(MiningController.class);

    @Autowired
    private IssueService issueService;
    @Autowired
    private ClusterService clusterService;
    @Autowired
    private StatisticService statisticService;

    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/cluster")
    public Object cluster(HttpServletRequest request) {
        String issueId = issueService.getCurrentIssueId(request);
        if (StringUtils.isBlank(issueId)) {
            return ResultUtil.errorWithMsg("get current issue failed,please create or select a issue");
        }
        IssueWithBLOBs issue = issueService.queryIssueById(issueId);
        List<String[]> content = null;
        try {
            content = (List<String[]>) ConvertUtil.convertBytesToObject(issue.getFilteredContent());
        } catch (Exception e) {
            return ResultUtil.errorWithMsg("query content from DB failed before cluster \t" + e.toString());
        }
        List<List<String[]>> clusterResult = clusterService.getClusterResult(content, Index.TITLE_INDEX);
        try {
            issue.setClusterResult(ConvertUtil.convertToBytes(clusterResult));
        } catch (Exception e) {
            logger.error("convert cluster result and origAndCount result to byte[] failed \t" + e.toString());
            return ResultUtil.unknowError();
        }
        if (issueService.updateIssueInfo(issue, request) == 0) {
            return ResultUtil.errorWithMsg("update DB failed after cluster and count");
        }
        return ResultUtil.success("mining complete");
    }

    @ResponseBody
    @RequestMapping("/calOrigAndCountResult")
    public Object calculateOrigAndCountResult(@RequestParam(value = "type", required = true) String type,
            HttpServletRequest request) {
        String issueId = issueService.getCurrentIssueId(request);
        if (StringUtils.isBlank(issueId)) {
            return ResultUtil.errorWithMsg("get current issue failed,please create or select a issue");
        }
        List<List<String[]>> list = null;
        if ("orig".equals(type)) {
            list = issueService.queryClusterResult(issueId);
        } else {
            list = issueService.queryModifiedClusterResult(issueId);
        }
        if (null == list) {
            return ResultUtil.errorWithMsg("query (modified)cluster result failed");
        }
        List<String[]> origAndCountResult = statisticService.getOrigAndCount(list, Index.TIME_INDEX);
        IssueWithBLOBs issue = new IssueWithBLOBs();
        issue.setIssueId(issueId);
        try {
            if ("orig".equals(type)) {
                issue.setOrigCountResult(ConvertUtil.convertToBytes(origAndCountResult));
            } else {
                issue.setModifiedOrigCountResult(ConvertUtil.convertToBytes(origAndCountResult));
            }
        } catch (Exception e) {
            logger.error("convert origAndCountResult failed");
            return ResultUtil.errorWithMsg("execute failed");
        }
        if (issueService.updateIssueInfo(issue, request) == 0) {
            return ResultUtil.errorWithMsg("execute failed");
        }
        return ResultUtil.success("execute success");
    }

    @ResponseBody
    @RequestMapping(value = "/statisticSingleSet")
    @SuppressWarnings("unchecked")
    public Object statistic(@RequestBody StatisticParams params, HttpServletRequest request) {
        String issueId = issueService.getCurrentIssueId(request);
        if (StringUtils.isBlank(issueId)) {
            return ResultUtil.errorWithMsg("get current issue failed,please create or select a issue");
        }
        IssueWithBLOBs issue = issueService.queryIssueById(issueId);
        try {
            List<String[]> list = null;
            if (Constant.TYPE_ORIG.equals(params.getType())) {
                list = ((List<List<String[]>>) ConvertUtil.convertBytesToObject(issue.getClusterResult()))
                        .get(params.getCurrentSet());
            } else {
                list = ((List<List<String[]>>) ConvertUtil.convertBytesToObject(issue.getModifiedClusterResult()))
                        .get(params.getCurrentSet());
            }
            Map<String, Map<String, Map<String, Integer>>> timeMap =
                    statisticService.processAll(list, params.getInterval());
            Map<String, Integer> typeMap = statisticService.getTypeCount(timeMap);
            Map<String, Integer> levelMap = statisticService.getLevelCount(timeMap);
            Map<String, Object> map = Maps.newHashMap();
            map.put("time", timeMap);
            Map<String, Object> countMap = Maps.newHashMap();
            countMap.put("type", typeMap);
            countMap.put("level", levelMap);
            map.put("count", countMap);
            return ResultUtil.success(map);
        } catch (Exception e) {
            logger.error("exception occur during statistic\t" + e.toString());
            return ResultUtil.errorWithMsg("统计失败");
        }
    }
}
