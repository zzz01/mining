package com.hust.service;

import java.util.List;
import java.util.Map;

import com.hust.model.StatisticCondition;

import net.sf.json.JSONObject;

public interface StatisticService {
    public List<String[]> getOrigAndCount(List<List<String[]>> list, int timeIndex);

    public Map<String, Integer> getIntervalCount(List<String> list, int interval);

    public Map<String, Integer> getEmotionTendencyCount(List<String> list);

    public Map<String, Integer> getInfoTypeCount(List<String> list);

    public Map<String, Integer> getMediaLevelCount(List<String> list);

    public Map<String, Integer> getNetizenAttention(Map<String, Integer> map);

    public Map<String, Integer> getMediaAttention(List<String> list);

    public JSONObject statistic(StatisticCondition sc);
}
