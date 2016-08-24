package com.hust.dao.mapper;

import java.util.List;

import com.hust.model.po.InfoType;
import com.hust.model.po.LMedia;

public interface InitialMapper {

	public List<LMedia> selectMedia();

	public List<InfoType> selectInfoType();
}
