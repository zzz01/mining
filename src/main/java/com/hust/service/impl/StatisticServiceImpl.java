package com.hust.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hust.constants.Emotion;
import com.hust.constants.Interval;
import com.hust.service.StatisticService;

@Service
public class StatisticServiceImpl implements StatisticService {

	@Override
	public List<String[]> getOrigAndCount(List<List<String[]>> setList, int timeIndex) {
		// TODO Auto-generated method stub
		List<String[]> reList = new ArrayList<String[]>();
		for (int i = 0; i < setList.size() - 1; i++) {
			List<String[]> tmpList = setList.get(i);
			int origIndex = -1;
			String origTime = "9999-12-12 23:59:59";
			for (int j = 0; j < tmpList.size(); j++) {
				String[] row = tmpList.get(j);
				if (origTime.compareTo(row[timeIndex]) > 0) {
					origTime = row[timeIndex];
					origIndex = j;
				}
			}
			if (origIndex == -1) {
				origIndex = 0;
			}
			String[] oldRow = tmpList.get(0);
			String[] newRow = new String[tmpList.get(0).length + 1];
			System.arraycopy(oldRow, 0, newRow, 1, oldRow.length);
			newRow[0] = tmpList.size() + "";
			reList.add(newRow);
		}
		List<String[]> singleList = setList.get(setList.size() - 1);
		for (int i = 0; i < singleList.size(); i++) {
			String[] oldRow = singleList.get(i);
			String[] newRow = new String[oldRow.length + 1];
			System.arraycopy(oldRow, 0, newRow, 1, oldRow.length);
			newRow[0] = "1";
			reList.add(newRow);
		}
		return reList;
	}

	@Override
	public Map<String, Integer> getIntervalCount(List<String> list, int interval) {
		// TODO Auto-generated method stub
		if (null == list || 0 == list.size()) {
			return null;
		}
		Map<String, Integer> map = new HashMap<String, Integer>();
		switch (interval) {
		case Interval.DAY: {
			for (String time : list) {
				if ("" != time && null != time) {
					time = time.substring(5, 9);
					if (null != map.get(time)) {
						map.put(time, map.get(time) + 1);
					} else {
						map.put(time, 1);
					}
				}
			}
		}
		case Interval.HOUR: {
			for (String time : list) {
				if ("" != time && null != time) {
					time = time.substring(5);
					if (null != map.get(time)) {
						map.put(time, map.get(time) + 1);
					} else {
						map.put(time, 1);
					}
				}
			}
		}
		case Interval.MONTH: {
			for (String time : list) {
				if ("" != time && null != time) {
					time = time.substring(0, 5);
					if (null != map.get(time)) {
						map.put(time, map.get(time) + 1);
					} else {
						map.put(time, 1);
					}
				}
			}
		}
		}

		return map;
	}

	@Override
	public Map<String, Integer> getEmotionTendencyCount(List<String> list) {
		// TODO Auto-generated method stub
		if (null == list || 0 == list.size())
			return null;
		Map<String, Integer> map = new HashMap<String, Integer>();
		int positive = 0, negative = 0, neutral = 0;
		for (String emotion : list) {
			if (emotion.matches(Emotion.POSITIVE)) {
				positive++;
			} else if (emotion.matches(Emotion.NEUTRAL)) {
				negative++;
			} else if (emotion.matches(Emotion.NEGATIVE)) {
				neutral++;
			}
		}
		map.put("positive", positive);
		map.put("neutral", neutral);
		map.put("negative", negative);
		return map;
	}

	@Override
	public Map<String, Integer> getResourceCount(List<String> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Integer> getTypeCount(List<String> list) {
		// TODO Auto-generated method stub
		return null;
	}

}
