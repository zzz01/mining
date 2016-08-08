package com.hust.dao.mapper;

import java.util.List;

import com.hust.model.InfoType;
import com.hust.model.MediaLevel;

public interface InitialMapper {

	public List<MediaLevel> selectMedia();

	public List<InfoType> selectInfoType();
}
