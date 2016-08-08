package com.hust.service.impl;

import java.util.List;

import com.hust.dao.InitialDao;
import com.hust.model.InfoType;
import com.hust.model.MediaLevel;
import com.hust.service.InitialService;

public class InitialServiceImpl implements InitialService {

	private InitialDao initialDao;

	@Override
	public List<MediaLevel> getMedia() {
		// TODO Auto-generated method stub
		return initialDao.getMedia();
	}

	@Override
	public List<InfoType> getInfoType() {
		// TODO Auto-generated method stub
		return initialDao.getInfoType();
	}

}
