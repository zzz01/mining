package com.hust.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Caculate {
    /**
     * 计算一条分词结果与一个集合的相似度
     * 
     * @param list
     *            所有数据的分词集合
     * @param str
     *            分词数组
     * @param set
     *            目标集合
     * @return
     */
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

    private static Map<String, Float> TF(String[] words) {
        if (words.length < 1) {
            return null;
        }
        float length = words.length;
        Map<String, Float> map = new HashMap<String, Float>();
        Map<String, Integer> freqMap = feq4Single(words);
        for (Entry<String, Integer> entry : freqMap.entrySet()) {
            map.put(entry.getKey(), (float) entry.getValue() / length);
        }
        return map;
    }

    public static List<Map<String, Float>> TFIDF(List<String[]> list) {
        if (null == list) {
            return null;
        }
        List<Map<String, Float>> listMap = new ArrayList<Map<String, Float>>();
        for (String[] row : list) {
            Map<String, Float> TF = TF(row);
            Map<String, Float> IDF = IDF(list, TF.keySet());
            Map<String, Float> map = new HashMap<String, Float>();
            for (Entry<String, Float> entry : TF.entrySet()) {
                String key = entry.getKey();
                map.put(key, entry.getValue() * IDF.get(key));
            }
            listMap.add(map);
        }
        return listMap;
    }

    /**
     * 计算一个文档中，每个词出现的频率
     * 
     * @param words
     * @return
     */
    private static Map<String, Integer> feq4Single(String[] words) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String word : words) {
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            } else {
                map.put(word, 1);
            }
        }
        return map;
    }

    /**
     * 计算
     * 
     * @param list
     * @param str
     * @return
     */
    private static Map<String, Float> IDF(List<String[]> list, Set<String> words) {
        if (words.size() < 1 || null == list || list.size() == 0) {
            return null;
        }
        Map<String, Float> map = new HashMap<String, Float>();
        for (String word : words) {
            float count = 0;
            for (String[] row : list) {
                for (String s : row) {
                    if (s.equals(word)) {
                        count++;
                        break;
                    }
                }
            }
            float value = (float) Math.log((float) list.size() / (float) (count + 1));
            map.put(word, value);
        }
        return map;
    }
}
