package com.hust.service.impl;

import com.hust.dao.InitialDao;
import com.hust.model.po.InfoType;
import com.hust.model.po.LMedia;
import com.hust.service.InitialService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
