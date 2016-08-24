package com.hust.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hust.model.po.InfoType;
import com.hust.model.po.LMedia;

@Service
public interface InitialService {
	public List<LMedia> getMedia();
	
	public List<InfoType> getInfoType();
}
