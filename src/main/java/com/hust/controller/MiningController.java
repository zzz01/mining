package com.hust.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hust.constants.Constants.Index;
import com.hust.model.IssueWithBLOBs;
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
        IssueWithBLOBs issue = issueService.getIssueById(issueId);
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
    @RequestMapping("/calculateOrigAndCountResult")
    public Object calculateOrigAndCountResult(HttpServletRequest request) {
        String issueId = issueService.getCurrentIssueId(request);
        if (StringUtils.isBlank(issueId)) {
            return ResultUtil.errorWithMsg("get current issue failed,please create or select a issue");
        }
        List<List<String[]>> clusterResult = issueService.queryClusterResult(issueId);
        if (null == clusterResult) {
            return ResultUtil.errorWithMsg("query cluster result failed");
        }
        List<String[]> origAndCountResult = statisticService.getOrigAndCount(clusterResult, Index.TIME_INDEX);
        IssueWithBLOBs issue = new IssueWithBLOBs();
        issue.setIssueId(issueId);
        try {
            issue.setOrigCountResult(ConvertUtil.convertToBytes(origAndCountResult));
        } catch (Exception e) {
            logger.error("convert origAndCountResult failed");
            return ResultUtil.errorWithMsg("execute failed");
        }
        if (issueService.updateIssueInfo(issue, request) == 0) {
            return ResultUtil.errorWithMsg("execute failed");
        }
        return ResultUtil.success("execute success");
    }

}
