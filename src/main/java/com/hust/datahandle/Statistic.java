package com.hust.datahandle;

import java.util.ArrayList;
import java.util.List;

public class Statistic {

	private int timeIndex;
	private List<List<String[]>> setList;

	public int getTimeIndex() {
		return timeIndex;
	}

	public void setTimeIndex(int timeIndex) {
		this.timeIndex = timeIndex;
	}

	public List<List<String[]>> getSetList() {
		return setList;
	}

	public void setSetList(List<List<String[]>> setList) {
		this.setList = setList;
	}

	public Statistic() {
	}

	public Statistic(int timeIndex, List<List<String[]>> setList) {
		this.timeIndex = timeIndex;
		this.setList = setList;
	}

	public List<String[]> getOrigAndCount() {
		List<String[]> list = new ArrayList<String[]>();
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
			list.add(newRow);
		}
		list.addAll(setList.get(setList.size() - 1));
		return list;
	}
}
