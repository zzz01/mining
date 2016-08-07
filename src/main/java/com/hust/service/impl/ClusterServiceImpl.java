package com.hust.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hust.constants.Config;
import com.hust.service.ClusterService;
import com.hust.util.Caculate;
import com.hust.util.WordSegment;

@Service
public class ClusterServiceImpl implements ClusterService {

	@Override
	public List<List<String[]>> getClusterResult(List<String[]> list, int targetIndex) {
		// TODO Auto-generated method stub
		List<String[]> segmentList = getSegmentList(list, targetIndex);
		List<List<Integer>> resultIndexSetList = cluster(segmentList);
		return convertToStringSet(list, resultIndexSetList, targetIndex);
	}

	private List<String[]> getSegmentList(List<String[]> list, int targetIndex) {
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

	private List<List<String[]>> convertToStringSet(List<String[]> list, List<List<Integer>> resultIndexSet,
			final int targetIndex) {
		if (null == resultIndexSet) {
			return null;
		}
		List<List<String[]>> listStrSet = new ArrayList<List<String[]>>();
		List<String[]> singleDataList = new ArrayList<String[]>();
		for (List<Integer> set : resultIndexSet) {
			if (set.size() == 1) {
				singleDataList.add(list.get(set.get(0)));
				continue;
			}
			List<String[]> setDataList = new ArrayList<String[]>();
			for (int i : set) {
				setDataList.add(list.get(i));
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
}
