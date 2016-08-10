package com.hust.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hust.constants.Media;
import com.hust.model.InfoType;
import com.hust.service.InitialService;

@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private InitialService initService;
	
	@ResponseBody
	@RequestMapping("/init")
	public Object init(){
		List<InfoType> infotype = initService.getInfoType();
		for (InfoType it : infotype) {
			Media.TYPE.add(it);
		}

		List<com.hust.model.LMedia> media = initService.getMedia();
		for (com.hust.model.LMedia m : media) {
			Media.LEVEL.add(m);
		}
		return Media.TYPE;
	}
}
