package com.hust.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hust.constants.Constants;
import com.hust.constants.Constants.Emotion;
import com.hust.constants.Constants.Index;
import com.hust.constants.Constants.Interval;
import com.hust.dao.WebsiteDao;
import com.hust.dao.WeightDao;
import com.hust.service.StatisticService;
import com.hust.util.CommonUtil;
import com.hust.util.TimeUtil;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private WebsiteDao websiteDao;
    @Autowired
    private WeightDao weightDao;

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
            String[] newRow = new String[oldRow.length + 1];
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
    public Map<String, Map<String, Map<String, Integer>>> processAll(List<String[]> list, int interval) {
        Map<String, Map<String, Map<String, Integer>>> map =
                new TreeMap<String, Map<String, Map<String, Integer>>>(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareTo(o2);
                    }
                });
        if (null == list || 0 == list.size()) {
            return map;
        }
        for (String[] array : list) {
            if (CommonUtil.isEmptyArray(array)) {
                continue;
            }
            String level = websiteDao.queryLevelByUrl(array[Index.URL_INDEX]);
            String type = websiteDao.queryTypeByUrl(array[Index.URL_INDEX]);
            String timeKey = getTimeKey(array[Index.TIME_INDEX], interval);
            Map<String, Map<String, Integer>> timeMap = map.get(timeKey);
            if (timeMap == null) {
                timeMap = new HashMap<String, Map<String, Integer>>();
                Map<String, Integer> typeMap = new HashMap<String, Integer>();
                Map<String, Integer> levelMap = new HashMap<String, Integer>();
                typeMap.put(type, 1);
                levelMap.put(level, 1);
                timeMap.put(Constants.MEDIA_CH, levelMap);
                timeMap.put(Constants.INFOTYPE_CH, typeMap);
                map.put(timeKey, timeMap);
            } else {
                Map<String, Integer> typeMap = timeMap.get(Constants.INFOTYPE_CH);
                if (null == typeMap) {
                    typeMap = new HashMap<String, Integer>();
                    typeMap.put(type, 1);
                } else {
                    typeMap.put(type, typeMap.get(type) + 1);
                }

                Map<String, Integer> levelMap = timeMap.get(Constants.MEDIA_CH);
                if (null == levelMap) {
                    levelMap = new HashMap<String, Integer>();
                    levelMap.put(level, 1);
                } else {
                    levelMap.put(level, levelMap.get(level) + 1);
                }
                timeMap.put(Constants.MEDIA_CH, levelMap);
                timeMap.put(Constants.INFOTYPE_CH, typeMap);
            }
        }
        for (String time : map.keySet()) {
            Map<String, Map<String, Integer>> timeMap = map.get(time);
            Map<String, Integer> mediaAttention = calculateAttention(timeMap.get(Constants.MEDIA_EN));
            Map<String, Integer> netizenAttention = calculateAttention(timeMap.get(Constants.INFOTYPE_EN));
            timeMap.put(Constants.NETIZENATTENTION_EN, netizenAttention);
            timeMap.put(Constants.MEDIA_EN, mediaAttention);
        }
        return map;
    }

    @Override
    public Map<String, Integer> getEmotionTendencyCount(List<String> list) {
        // TODO Auto-generated method stub
        Map<String, Integer> map = new HashMap<String, Integer>();
        if (null == list || 0 == list.size())
            return map;
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
        map.put(Emotion.POSITIVE, positive);
        map.put(Emotion.NEUTRAL, neutral);
        map.put(Emotion.NEGATIVE, negative);
        return map;
    }

    private String getTimeKey(String time, int interval) {
        if (StringUtils.isBlank(time) || !TimeUtil.isvalidate(time)) {
            return Constants.INVALID_TIME;
        }
        switch (interval) {
            case Interval.DAY: {
                return time.substring(5, 10);
            }
            case Interval.HOUR: {
                return time.substring(5, 13);
            }
            case Interval.MONTH: {
                return time.substring(0, 7);
            }
            default: {
                return Constants.INVALID_TIME;
            }
        }
    }

    @Override
    public Map<String, Integer> getTypeCount(Map<String, Map<String, Map<String, Integer>>> map) {
        // TODO Auto-generated method stub
        Map<String, Integer> countMap = new HashMap<String, Integer>();
        if (null == map) {
            return countMap;
        }
        for (Map<String, Map<String, Integer>> values : map.values()) {
            Map<String, Integer> typeMap = values.get(Constants.INFOTYPE_CH);
            for (Entry<String, Integer> entry : typeMap.entrySet()) {
                countMap.put(entry.getKey(), entry.getValue() + countMap.get(entry.getKey()));
            }
        }
        return countMap;
    }

    @Override
    public Map<String, Integer> getLevelCount(Map<String, Map<String, Map<String, Integer>>> map) {
        // TODO Auto-generated method stub
        Map<String, Integer> countMap = new HashMap<String, Integer>();
        if (null == map) {
            return countMap;
        }
        for (Map<String, Map<String, Integer>> values : map.values()) {
            Map<String, Integer> typeMap = values.get(Constants.MEDIA_CH);
            for (Entry<String, Integer> entry : typeMap.entrySet()) {
                countMap.put(entry.getKey(), entry.getValue() + countMap.get(entry.getKey()));
            }
        }
        return countMap;
    }

    @Override
    public Map<String, Integer> calculateAttention(Map<String, Integer> map) {
        Map<String, Integer> attention = new HashMap<String, Integer>();
        if (null == map) {
            return attention;
        }
        for (Entry<String, Integer> entry : map.entrySet()) {
            int weight = weightDao.queryWeightByName(entry.getKey());
            int atten = weight * entry.getValue();
            attention.put(entry.getKey(), atten);
        }
        return attention;
    }

}
