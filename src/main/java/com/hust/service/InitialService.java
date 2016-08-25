package com.hust.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hust.model.po.InfoType;

@Service
public interface InitialService {
    public Map<String, String> getMedia();

    public Map<String, Integer> getInfoType();

    public Map<String, Integer> getLevel();
}
