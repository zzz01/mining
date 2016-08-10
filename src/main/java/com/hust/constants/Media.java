package com.hust.constants;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.hust.model.InfoType;
import com.hust.model.LMedia;
import com.hust.service.InitialService;

public class Media {

	public static final List<LMedia> LEVEL = new ArrayList<LMedia>();

	public static final List<InfoType> TYPE = new ArrayList<InfoType>();

	@Autowired
	public InitialService initialService;

	@PostConstruct
	public void init(){
		List<InfoType> infotype = initialService.getInfoType();
		for (InfoType it : infotype) {
			TYPE.add(it);
		}

		List<com.hust.model.LMedia> media = initialService.getMedia();
		for (com.hust.model.LMedia m : media) {
			LEVEL.add(m);
		}
	}

	public static int getInfoTypeWeightByName(String name) {
		for (InfoType it : TYPE) {
			if (name == it.getName()) {
				return it.getWeight();
			}
		}
		return 0;
	}

	public static int getMediaWeightByName(String name) {
		for (LMedia ml : LEVEL) {
			if (name == ml.getName()) {
				return ml.getWeight();
			}
		}
		return 0;
	}
}
