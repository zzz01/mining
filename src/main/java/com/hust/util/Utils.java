package com.hust.util;

import java.util.ArrayList;
import java.util.List;

public class Utils {
	public static List<String[]> convertoStrList(List<List<String[]>> setList) {
		if (null == setList)
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
			listStr.add(new String[set.get(0).length]);
		}
		return listStr;
	}
}
