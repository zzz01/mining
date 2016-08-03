package com.hust.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

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
	public List<String[]> getIntervalCount(List<List<String[]>> list) {
		// TODO Auto-generated method stub
		return null;
	}

}
