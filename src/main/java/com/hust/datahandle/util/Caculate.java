package com.hust.datahandle.util;

import java.util.List;

public class Caculate {
    public static float sim(List<String[]> list, String[] str, List<Integer> set) {
        if (str.length == 0 || set.size() == 0) {
            return 0.0f;
        }
        float sum = 0.0f;
        for (int i : set) {
            sum += sim(str, list.get(i));
        }
        return sum / set.size();
    }

    private static float sim(String[] str1, String[] str2) {
        if (str1.length == 0 || str2.length == 0) {
            return 0.0f;
        }
        int count = 0;
        for (String word1 : str1) {
            for (String word2 : str2) {
                if (word1.equals(word2)) {
                    count++;
                    break;
                }
            }
        }
        float sim1 = (float) count / (float) str1.length;
        count = 0;
        for (String word2 : str2) {
            for (String word1 : str1) {
                if (word2.equals(word1)) {
                    count++;
                    break;
                }
            }
        }
        float sim2 = (float) count / (float) str2.length;
        return (sim1 + sim2) / 2.0f;
    }
}
