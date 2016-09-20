package com.hust.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hust.constants.Constants;
import com.hust.model.Condition;
import com.hust.model.IssueInfoWithBLOBs;
import com.hust.model.StatisticCondition;
import com.hust.service.ClusterService;
import com.hust.service.IssueService;
import com.hust.service.StatisticService;
import com.hust.util.ConvertUtil;
import com.hust.util.PaintUtil;
import com.hust.util.ResultUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/data")
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

    @ResponseBody
    @RequestMapping(value = "/handle", method = RequestMethod.POST)
    public Object cluster(@RequestParam(value = "file", required = true) MultipartFile file,
            @RequestParam(value = "targetIndex", required = true) int targetIndex,
            @RequestParam(value = "timeIndex", required = true) int timeIndex,
            @RequestParam(value = "sourceIndex", required = true) int resourceIndex,
            @RequestParam(value = "typeIndex", required = true) int typeIndex,
            @RequestParam(value = "emotionIndex", required = true) int emotionIndex,
            @RequestParam(value = "sourceType", required = false) String sourceType, HttpServletRequest request) {
        // String userName = userService.getCurrentUser(request);
        // List<String[]> list = uploadService.readDataFromExcel(file, "微博", userName);
        // if (null == list || list.size() == 0) {
        // return ResultUtil.errorWithMsg("文件是空的");
        // }
        //
        // List<List<String[]>> list_res = clusterService.getClusterResult(list, targetIndex);
        // if (null == list_res) {
        // return ResultUtil.errorWithMsg("文件解析出错");
        // }
        // List<String[]> clusterList = ConvertUtil.convertoStrList(list_res);
        // List<String[]> origAndCountList = statisticService.getOrigAndCount(list_res, timeIndex);
        // List<String> emotionlist = new ArrayList<String>();
        // List<String> resourceList = new ArrayList<String>();
        // List<String> typeList = new ArrayList<String>();
        // List<String> intervalList = new ArrayList<String>();
        // for (int i = 0; i < clusterList.size() - 1; i++) {
        // String[] row = clusterList.get(i);
        // emotionlist.add(row[emotionIndex]);
        // resourceList.add(row[resourceIndex]);
        // typeList.add(row[typeIndex]);
        // intervalList.add(row[timeIndex]);
        // }
        // Map<String, Integer> emotionMap = statisticService.getEmotionTendencyCount(emotionlist);
        // Map<String, Integer> resourceMap = statisticService.getMediaLevelCount(resourceList);
        // Map<String, Integer> typeMap = statisticService.getInfoTypeCount(typeList);
        // Map<String, Integer> netizenAtten = statisticService.getNetizenAttention(typeMap);
        // Map<String, Integer> mediaAtten = statisticService.getMediaAttention(resourceList);
        // Map<String, Integer> interval = statisticService.getIntervalCount(intervalList, Interval.DAY);
        // Map<String, Object> map = new HashMap<String, Object>();
        // map.put("cluster", clusterList);
        // map.put("origcount", origAndCountList);
        // map.put("emotion", emotionMap);
        // map.put("resource", resourceMap);
        // map.put("type", typeMap);
        // map.put("netizenAtten", netizenAtten);
        // map.put("mediaAtten", mediaAtten);
        // map.put("interval", interval);
        // map.put("msg", "success");
        // JSONObject json = PaintUtil.convertMap1(interval);
        // return json;
        return null;
    }

    @ResponseBody
    @RequestMapping("/statistic")
    public Object statistic(@RequestBody Condition condition, HttpServletRequest request) {
        String fileUUID = request.getSession().getAttribute(Constants.ISSUE_ID).toString();
        if (StringUtils.isBlank(fileUUID)) {
            return ResultUtil.errorWithMsg("无法从session中获取到文件uuid，请尝试重新登录");
        }
        IssueInfoWithBLOBs issue = issueService.getByUUID(fileUUID);
        List<String[]> list;
        try {
            list = (List<String[]>) ConvertUtil.convertBytesToObject(issue.getContent());
            if (null == list || list.isEmpty()) {
                return ResultUtil.errorWithMsg("无法从数据库中获取文件，请重新上传");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.info(e.toString());
            return ResultUtil.errorWithMsg("无法从数据库中获取文件，请重新上传");
        }
        int targetIndex = condition.getTargetIndex();
        List<List<String[]>> list_res = clusterService.getClusterResult(list, targetIndex);
        if (null == list_res) {
            return ResultUtil.errorWithMsg("文件解析出错");
        }
        List<String[]> clusterList = ConvertUtil.convertoStrList(list_res);
        StatisticCondition sc = new StatisticCondition();
        sc.setList(clusterList);
        sc.setEmotionIndex(condition.getEmotionIndex());
        sc.setInfoTypeIndex(condition.getInfoTypeIndex());
        sc.setMediaIndex(condition.getMediaIndex());
        sc.setTimeIndex(condition.getTimeIndex());
        sc.setInterval(condition.getInterval());
        JSONObject statResultJson = statisticService.statistic(sc);
        String filename = issue.getIssueName();
        statResultJson.put("title", filename.substring(0, filename.indexOf(".")));
        JSONObject paintJson = PaintUtil.convertPaintJson(statResultJson);

        JSONObject clusterResult = new JSONObject();
        JSONArray head = new JSONArray();
        JSONArray body = new JSONArray();
        for (String str : list.get(0)) {
            if (StringUtils.isBlank(str)) {
                head.add("");
            } else {
                head.add(str);
            }
        }
        int length = clusterList.size() > 11 ? 11 : clusterList.size();
        for (int i = 1; i < length; i++) {
            JSONArray line = new JSONArray();
            String[] lineArray = clusterList.get(i);
            for (String str : lineArray) {
                if (StringUtils.isBlank(str)) {
                    line.add("");
                } else {
                    line.add(str);
                }
            }
            body.add(line);
        }
        clusterResult.put("head", head);
        clusterResult.put("body", body);
        paintJson.put(Constants.CLUSTER_RESULT_EN, clusterResult);
        try {
            issue.setResultList(ConvertUtil.convertToBytes(clusterList));
            // issue.setResultJson(ConvertUtil.convertToBytes(clusterResult));
            issue.setPaintJson(ConvertUtil.convertToBytes(paintJson));
            issueService.updateIssueInfo(issue);
        } catch (Exception e) {
            logger.error("update issue_info fail\t" + e.toString());
        }
        return ResultUtil.success(paintJson);
    }
}
