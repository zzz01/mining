package com.hust.service.impl;

import java.util.List;

import com.hust.dao.InitialDao;
import com.hust.service.InitialService;

public class InitialServiceImpl implements InitialService {

	private InitialDao initialDao;

	@Override
	public List<String> getZhongyangMedia() {
		// TODO Auto-generated method stub
		return initialDao.getZhongyangMedia();
	}

	@Override
	public List<String> getShengjiMedia() {
		// TODO Auto-generated method stub
		return initialDao.getShengjiMedia();
	}

	@Override
	public List<String> getOhterMedia() {
		// TODO Auto-generated method stub
		return initialDao.getOtherMedia();
	}

}
