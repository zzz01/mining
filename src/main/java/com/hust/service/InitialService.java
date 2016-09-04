package com.hust.service;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface InitialService {
    Map<String, String> getMedia();

    Map<String, Integer> getInfoType();

    Map<String, Integer> getLevel();

    void loadStopwords();
}
