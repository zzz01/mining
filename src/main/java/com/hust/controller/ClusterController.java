package com.hust.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.hust.constants.Interval;
import com.hust.service.ClusterService;
import com.hust.service.StatisticService;
import com.hust.service.UploadService;
import com.hust.util.ConvertUtil;
import com.hust.util.PaintUtil;

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

	@ResponseBody
	@RequestMapping(value = "/handle", method = RequestMethod.POST)
	public Object cluster(@RequestParam(value = "file", required = true) MultipartFile file,
			@RequestParam(value = "targetIndex", required = true) int targetIndex,
			@RequestParam(value = "timeIndex", required = true) int timeIndex,
			@RequestParam(value = "sourceIndex", required = true) int resourceIndex,
			@RequestParam(value = "typeIndex", required = true) int typeIndex,
			@RequestParam(value = "emotionIndex", required = true) int emotionIndex) {

		InputStream is;
		try {
			is = file.getInputStream();
		} catch (IOException e) {
			LOG.error("Upload file error \t" + e.toString());
			return new JSONObject().put("msg", e.toString());
		}

		List<String[]> list = uploadService.readDataFromExcel(is);
		if (null == list) {
			return new JSONObject().put("msg", "文件是空的");
		}
		List<List<String[]>> list_res = clusterService.getClusterResult(list, targetIndex);
		if (null == list_res) {
			return new JSONObject().put("msg", "解析出错");
		}
		List<String[]> clusterList = ConvertUtil.convertoStrList(list_res);
		List<String[]> origAndCountList = statisticService.getOrigAndCount(list_res, timeIndex);
		List<String> emotionlist = new ArrayList<String>();
		List<String> resourceList = new ArrayList<String>();
		List<String> typeList = new ArrayList<String>();
		List<String> intervalList = new ArrayList<String>();
		for (String[] row : list) {
			emotionlist.add(row[emotionIndex]);
			resourceList.add(row[resourceIndex]);
			typeList.add(row[typeIndex]);
			intervalList.add(row[timeIndex]);
		}
//		Map<String, Integer> emotionMap = statisticService.getEmotionTendencyCount(emotionlist);
//		Map<String, Integer> resourceMap = statisticService.getMediaLevelCount(resourceList);
//		Map<String, Integer> typeMap = statisticService.getInfoTypeCount(typeList);
//		Map<String, Integer> netizenAtten = statisticService.getNetizenAttention(typeList);
//		Map<String, Integer> mediaAtten = statisticService.getMediaAttention(resourceList);
		Map<String, Integer> interval = statisticService.getIntervalCount(intervalList, Interval.DAY);
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("cluster", clusterList);
//		map.put("origcount", origAndCountList);
//		map.put("emotion", emotionMap);
//		map.put("resource", resourceMap);
//		map.put("type", typeMap);
//		map.put("netizenAtten", netizenAtten);
//		map.put("mediaAtten", mediaAtten);
//		map.put("interval", interval);
//		map.put("msg", "success");
		JSONObject json = PaintUtil.convertMap(interval);
		return json;
	}

	@ResponseBody
	@RequestMapping("/statistic")
	public Object statistic(List<String[]> list, String type) {

		return "";
	}
}
