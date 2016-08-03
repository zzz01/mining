package com.hust.datahandle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.hust.constants.Config;
import com.hust.datahandle.util.Caculate;
import com.hust.datahandle.util.WordSegment;

public class Cluster {
	private List<String[]> list;
	private int targetIndex;
	private List<List<String[]>> resultSetList;

	public List<String[]> getList() {
		return list;
	}

	public void setList(List<String[]> list) {
		this.list = list;
	}

	public int getTargetIndex() {
		return targetIndex;
	}

	public void setTargetIndex(int targetIndex) {
		this.targetIndex = targetIndex;
	}

	public List<List<String[]>> getResultSetList() {
		return resultSetList;
	}

	public Cluster() {
	}

	public Cluster(List<String[]> list, int targetRow) {
		this.list = list;
		this.targetIndex = targetRow;
	}

	public List<String[]> getResult() {
		List<String[]> listSeg = getSegmentList();
		List<List<Integer>> indexSetList = cluster(listSeg);
		List<List<String[]>> strSetList = convertToStringSet(indexSetList);
		resultSetList = strSetList;
		List<String[]> result = convertoStr(strSetList);
		return result;
	}

	/**
	 * @Title: getSegmentList @Description: 得到目标列中句子的分词结果，并保存起来 @param @return
	 *         void @throws
	 */
	private List<String[]> getSegmentList() {
		if (null == list || 0 == list.size()) {
			return null;
		}
		if (targetIndex < 0) {
			return null;
		}
		List<String[]> listSeg = new ArrayList<String[]>();
		WordSegment ws = WordSegment.getInstance();
		for (String[] row : list) {
			listSeg.add(ws.parse(row[targetIndex]));
		}
		return listSeg;
	}

	private List<List<Integer>> cluster(List<String[]> list) {
		if (null == list || list.size() == 0) {
			return null;
		}
		List<List<Integer>> resultIndexSet = new ArrayList<List<Integer>>();
		for (int i = 0; i < list.size(); i++) {
			int maxSimSetIndex = -1;
			float maxSim = -1.0f;
			if (i == 0) {
				List<Integer> set = new ArrayList<Integer>();
				set.add(0);
				resultIndexSet.add(set);
				continue;
			}
			for (int j = 0; j < resultIndexSet.size(); j++) {
				float sim = Caculate.sim(list, list.get(i), resultIndexSet.get(j));
				if (maxSim < sim) {
					maxSim = sim;
					maxSimSetIndex = j;
				}
			}
			if (maxSim <= Config.SIMILARITYTHRESHOLD) {
				List<Integer> set = new ArrayList<Integer>();
				set.add(i);
				resultIndexSet.add(set);
			} else {
				resultIndexSet.get(maxSimSetIndex).add(i);
			}
		}
		Collections.sort(resultIndexSet, new Comparator<List<Integer>>() {
			public int compare(List<Integer> o1, List<Integer> o2) {
				return o2.size() - o1.size();
			}

		});
		return resultIndexSet;
	}

	private List<List<String[]>> convertToStringSet(List<List<Integer>> list) {
		if (null == list) {
			return null;
		}
		List<List<String[]>> listStrSet = new ArrayList<List<String[]>>();
		List<String[]> singleDataList = new ArrayList<String[]>();
		for (List<Integer> set : list) {
			if (set.size() == 1) {
				singleDataList.add(this.list.get(set.get(0)));
				continue;
			}
			List<String[]> setDataList = new ArrayList<String[]>();
			for (int i : set) {
				setDataList.add(this.list.get(i));
			}
			Collections.sort(setDataList, new Comparator<String[]>() {
				public int compare(String[] o1, String[] o2) {
					return o1[targetIndex].compareTo(o2[targetIndex]);
				}
			});
			listStrSet.add(setDataList);
		}

		Collections.sort(singleDataList, new Comparator<String[]>() {
			public int compare(String[] o1, String[] o2) {
				return o1[targetIndex].compareTo(o2[targetIndex]);
			}
		});
		listStrSet.add(singleDataList);
		return listStrSet;
	}

	private List<String[]> convertoStr(List<List<String[]>> setList) {
		if (null == list)
			return null;
		List<String[]> listStr = new ArrayList<String[]>();
		// listStr.add(this.list.get(0));
		for (List<String[]> set : setList) {
			for (String[] row : set) {
				listStr.add(row);
			}
			/**
			 * 新加一行空行，以区分类间的区别
			 */
			listStr.add(new String[this.list.get(0).length]);
		}
		return listStr;
	}
}
