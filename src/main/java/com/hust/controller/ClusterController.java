package com.hust.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hust.constants.Constants;
import com.hust.constants.Interval;
import com.hust.model.StatisticCondition;
import com.hust.service.ClusterService;
import com.hust.service.StatisticService;
import com.hust.service.UploadService;
import com.hust.service.UserService;
import com.hust.util.ConvertUtil;
import com.hust.util.PaintUtil;
import com.hust.util.ResultUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/data")
public class ClusterController {
    /**
     * Logger for this class
     */
    private static final Logger LOG = LoggerFactory.getLogger(ClusterController.class);

    @Autowired
    private UploadService uploadService;

    @Autowired
    private ClusterService clusterService;

    @Autowired
    private StatisticService statisticService;

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/handle", method = RequestMethod.POST)
    public Object cluster(@RequestParam(value = "file", required = true) MultipartFile file,
            @RequestParam(value = "targetIndex", required = true) int targetIndex,
            @RequestParam(value = "timeIndex", required = true) int timeIndex,
            @RequestParam(value = "sourceIndex", required = true) int resourceIndex,
            @RequestParam(value = "typeIndex", required = true) int typeIndex,
            @RequestParam(value = "emotionIndex", required = true) int emotionIndex,
            @RequestParam(value = "sourceType", required = false) String sourceType, HttpServletRequest request) {
        String userName = userService.getCurrentUser(request);
        List<String[]> list = uploadService.readDataFromExcel(file, "微博", userName);
        if (null == list || list.size() == 0) {
            return ResultUtil.errorWithMsg("文件是空的");
        }

        List<List<String[]>> list_res = clusterService.getClusterResult(list, targetIndex);
        if (null == list_res) {
            return ResultUtil.errorWithMsg("文件解析出错");
        }
        List<String[]> clusterList = ConvertUtil.convertoStrList(list_res);
        List<String[]> origAndCountList = statisticService.getOrigAndCount(list_res, timeIndex);
        List<String> emotionlist = new ArrayList<String>();
        List<String> resourceList = new ArrayList<String>();
        List<String> typeList = new ArrayList<String>();
        List<String> intervalList = new ArrayList<String>();
        for (int i = 0; i < clusterList.size() - 1; i++) {
            String[] row = clusterList.get(i);
            emotionlist.add(row[emotionIndex]);
            resourceList.add(row[resourceIndex]);
            typeList.add(row[typeIndex]);
            intervalList.add(row[timeIndex]);
        }
        Map<String, Integer> emotionMap = statisticService.getEmotionTendencyCount(emotionlist);
        Map<String, Integer> resourceMap = statisticService.getMediaLevelCount(resourceList);
        Map<String, Integer> typeMap = statisticService.getInfoTypeCount(typeList);
        Map<String, Integer> netizenAtten = statisticService.getNetizenAttention(typeMap);
        Map<String, Integer> mediaAtten = statisticService.getMediaAttention(resourceList);
        Map<String, Integer> interval = statisticService.getIntervalCount(intervalList, Interval.DAY);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("cluster", clusterList);
        map.put("origcount", origAndCountList);
        map.put("emotion", emotionMap);
        map.put("resource", resourceMap);
        map.put("type", typeMap);
        map.put("netizenAtten", netizenAtten);
        map.put("mediaAtten", mediaAtten);
        map.put("interval", interval);
        map.put("msg", "success");
        JSONObject json = PaintUtil.convertMap1(interval);
        return json;
    }

    @ResponseBody
    @RequestMapping("/statistic")
    public Object statistic(@RequestParam(value = "file", required = true) MultipartFile file,
            @RequestParam(value = "select_target", required = true) int targetIndex,
            @RequestParam(value = "select_time", required = true) int timeIndex,
            @RequestParam(value = "select_media", required = true) int resourceIndex,
            @RequestParam(value = "select_infotype", required = true) int typeIndex,
            @RequestParam(value = "select_emotion", required = true) int emotionIndex,
            @RequestParam(value = "select_sourcetype", required = true) String sourceType,
            @RequestParam(value = "select_interval", required = true) int interval, HttpServletRequest request) {
        String userName = userService.getCurrentUser(request);
        List<String[]> list = uploadService.readDataFromExcel(file, sourceType, userName);
        if (null == list || list.size() == 0) {
            return ResultUtil.errorWithMsg("文件是空的");
        }
        List<List<String[]>> list_res = clusterService.getClusterResult(list, targetIndex);
        if (null == list_res) {
            return ResultUtil.errorWithMsg("文件解析出错");
        }
        List<String[]> clusterList = ConvertUtil.convertoStrList(list_res);
        StatisticCondition sc = new StatisticCondition();
        sc.setList(clusterList);
        sc.setEmotionIndex(emotionIndex);
        sc.setInfoTypeIndex(typeIndex);
        sc.setMediaIndex(resourceIndex);
        sc.setTimeIndex(timeIndex);
        sc.setInterval(interval);
        JSONObject json = statisticService.statistic(sc);
        String filename = file.getOriginalFilename();
        json.put("title", filename.substring(0, filename.indexOf(".")));
        JSONObject paintJson = PaintUtil.convertPaintJson(json);

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
        for (int i = 0; i < clusterList.size(); i++) {
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
        return ResultUtil.success(paintJson);
    }
}
