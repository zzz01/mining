package com.hust.constants;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hust.service.InitialService;

public class MediaLevel {

	public static final List<String> ZHONGYANG = null;
	public static final List<String> SHENGJI = null;
	public static final List<String> OTHERS = null;

	@Autowired
	public static InitialService initialService;
	static {
		List<String> zhongyang = initialService.getZhongyangMedia();
		for (String media : zhongyang) {
			ZHONGYANG.add(media);
		}

		List<String> shengji = initialService.getShengjiMedia();
		for (String media : shengji) {
			SHENGJI.add(media);
		}

		List<String> other = initialService.getOhterMedia();
		for (String media : other) {
			OTHERS.add(media);
		}
	}
}
