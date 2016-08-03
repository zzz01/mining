package com.hust.service;

import java.util.List;

public interface StatisticService {
	public List<String[]> getOrigAndCount(List<List<String[]>> list, int timeIndex);

	public List<String[]> getIntervalCount(List<List<String[]>> list);
}
