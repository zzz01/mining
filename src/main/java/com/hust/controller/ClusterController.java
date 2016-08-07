package com.hust.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hust.service.ClusterService;
import com.hust.service.StatisticService;
import com.hust.service.UploadService;
import com.hust.util.ConvertUtil;

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

	@RequestMapping(value = "/handle", method = RequestMethod.POST)
	public ModelAndView cluster(@RequestParam(value = "file", required = true) MultipartFile file,
			@RequestParam(value = "targetIndex", required = true) int targetIndex,@RequestParam(value = "timeIndex", required = true) int timeIndex) {

		InputStream is;
		try {
			is = file.getInputStream();
		} catch (IOException e) {
			LOG.error("Upload file error \t" + e.toString());
			return new ModelAndView("error.jsp").addObject("msg", e.toString());
		}

		List<String[]> list = uploadService.readDataFromExcel(is);
		if (null == list) {
			return new ModelAndView("error.jsp").addObject("msg", "文件是空的");
		}
		List<List<String[]>> list_res = clusterService.getClusterResult(list, targetIndex);
		if (null == list_res) {
			return new ModelAndView("error.jsp").addObject("msg", "解析出错");
		}
		List<String[]> clusterList = ConvertUtil.convertoStrList(list_res);
		List<String[]> origAndCountList = statisticService.getOrigAndCount(list_res, timeIndex);
		ModelAndView mav = new ModelAndView("show.jsp");
		mav.addObject("clusterList", clusterList);
		mav.addObject("origAndCountList", origAndCountList);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping("/statistic")
	public Object statistic(List<String[]> list,String type){
		
		return "";
	}
}
