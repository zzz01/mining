package com.hust.dao.mapper;

import java.util.List;

import com.hust.model.InfoType;
import com.hust.model.LMedia;

public interface InitialMapper {

	public List<LMedia> selectMedia();

	public List<InfoType> selectInfoType();
}
