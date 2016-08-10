package com.hust.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hust.dao.mapper.InitialMapper;
import com.hust.model.InfoType;
import com.hust.model.LMedia;

@Repository
public class InitialDao {

	@Autowired
	private InitialMapper initialMapper;

	public List<LMedia> getMedia() {
		return initialMapper.selectMedia();
	}

	public List<InfoType> getInfoType() {
		return initialMapper.selectInfoType();
	}
}
