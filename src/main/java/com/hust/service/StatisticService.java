package com.hust.service;

import java.util.List;
import java.util.Map;

public interface StatisticService {
	public List<String[]> getOrigAndCount(List<List<String[]>> list, int timeIndex);

	public Map<String, Integer> getIntervalCount(List<String> list, int interval);

	public Map<String, Integer> getEmotionTendencyCount(List<String> list);

	public Map<String, Integer> getResourceCount(List<String> list);

	public Map<String, Integer> getTypeCount(List<String> list);
}
