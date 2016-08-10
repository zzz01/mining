package com.hust.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hust.service.StatisticService;

@Controller
@RequestMapping("/test")
public class StatisticController {

	@Autowired
	private StatisticService statService;

	@ResponseBody
	@RequestMapping("/origandcount")
	public Object getOrigAndCount(@RequestParam(value = "list", required = true) List<List<String[]>> list,
			@RequestParam(value = "timeIndex", required = false) int timeIndex) {
		return statService.getOrigAndCount(list, timeIndex);
	}

	@ResponseBody
	@RequestMapping("/intervalcount")
	public Object getIntervalCount(List<String> list, int interval) {
		return statService.getIntervalCount(list, interval);
	}
	
	

}
