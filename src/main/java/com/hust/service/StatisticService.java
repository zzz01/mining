package com.hust.service;

import java.util.List;
import java.util.Map;

public interface StatisticService {
    public List<String[]> getOrigAndCount(List<List<String[]>> list, int timeIndex);

    public Map<String, Map<String, Map<String, Integer>>> processAll(List<String[]> list, int interval);

    public Map<String, Integer> getEmotionTendencyCount(List<String> list);

    public Map<String, Integer> getTypeCount(Map<String, Map<String, Map<String, Integer>>> map);

    public Map<String, Integer> getLevelCount(Map<String, Map<String, Map<String, Integer>>> map);

    public Map<String,Integer> calculateAttention(Map<String, Integer> map);

}
