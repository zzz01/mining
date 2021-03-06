package com.hust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hust.dao.InitialDao;
import com.hust.model.InfoType;
import com.hust.model.LMedia;
import com.hust.service.InitialService;

public class InitialServiceImpl implements InitialService {

	@Autowired
	private InitialDao initialDao;

	@Override
	public List<LMedia> getMedia() {
		// TODO Auto-generated method stub
		return initialDao.getMedia();
	}

	@Override
	public List<InfoType> getInfoType() {
		// TODO Auto-generated method stub
		return initialDao.getInfoType();
	}

}
