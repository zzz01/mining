package com.hust.service;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface InitialService {
    public Map<String, String> getMedia();

    public Map<String, Integer> getInfoType();

    public Map<String, Integer> getLevel();
}
