package com.hust.service;

import java.util.List;

import com.hust.model.InfoType;
import com.hust.model.MediaLevel;

public interface InitialService {
	public List<MediaLevel> getMedia();
	
	public List<InfoType> getInfoType();
}
