package com.hust.dao;

import java.util.List;

import com.hust.dao.mapper.InitialMapper;

public class InitialDao {

	private InitialMapper initialMapper;

	public List<String> getZhongyangMedia() {
		return initialMapper.selectMedia("");
	}

	public List<String> getShengjiMedia() {
		return initialMapper.selectMedia("");
	}

	public List<String> getOtherMedia() {
		return initialMapper.selectMedia("");
	}
}
