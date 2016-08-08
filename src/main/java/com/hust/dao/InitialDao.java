package com.hust.dao;

import java.util.List;

import com.hust.dao.mapper.InitialMapper;
import com.hust.model.InfoType;
import com.hust.model.MediaLevel;

public class InitialDao {

	private InitialMapper initialMapper;

	public List<MediaLevel> getMedia() {
		return initialMapper.selectMedia();
	}

	public List<InfoType> getInfoType() {
		return initialMapper.selectInfoType();
	}
}
