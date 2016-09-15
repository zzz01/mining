package com.hust.util;

import java.util.ArrayList;
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
                    System.out.println(row[1]);
                }
                /**
                 * 新加一行空行，以区分类间的区别
                 */
                listStr.add(new String[set.get(0).length]);
                System.out.println();
            }
        }
        return listStr;
    }
}
