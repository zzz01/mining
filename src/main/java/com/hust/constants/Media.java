package com.hust.constants;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hust.model.InfoType;
import com.hust.model.MediaLevel;
import com.hust.service.InitialService;

public class Media {

	public static final List<MediaLevel> LEVEL = null;

	public static final List<InfoType> INFOTYPE = null;

	@Autowired
	public static InitialService initialService;

	static {
		List<InfoType> infotype = initialService.getInfoType();
		for (InfoType it : infotype) {
			INFOTYPE.add(it);
		}

		List<com.hust.model.MediaLevel> media = initialService.getMedia();
		for (com.hust.model.MediaLevel m : media) {
			LEVEL.add(m);
		}
	}

	public static int getInfoTypeWeightByName(String name) {
		for (InfoType it : INFOTYPE) {
			if (name == it.getName()) {
				return it.getWeight();
			}
		}
		return 0;
	}

	public static int getMediaWeightByName(String name) {
		for (MediaLevel ml : LEVEL) {
			if (name == ml.getName()) {
				return ml.getWeight();
			}
		}
		return 0;
	}
}
