package com.hust.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ConvertUtil {
    public static List<String[]> convertoStrList(List<List<String[]>> setList) {
        if (null == setList)
            return null;
        List<String[]> listStr = new ArrayList<String[]>();
        // listStr.add(this.list.get(0));
        for (List<String[]> set : setList) {
            if (null != set && set.size() != 0) {
                for (String[] row : set) {
                    listStr.add(row);
                }
                /**
                 * 新加一行空行，以区分类间的区别
                 */
                listStr.add(new String[set.get(0).length]);
            }
        }
        return listStr;
    }

    public static List<List<String[]>> convertToStringSet(List<String[]> list, List<List<Integer>> resultIndexSet,
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
        Collections.sort(listStrSet, new Comparator<List<String[]>>() {

            @Override
            public int compare(List<String[]> o1, List<String[]> o2) {
                // TODO Auto-generated method stub
                return o2.size() - o1.size();
            }
        });
        return listStrSet;
    }
}
