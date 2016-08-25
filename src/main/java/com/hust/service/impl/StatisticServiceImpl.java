package com.hust.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.hust.constants.Emotion;
import com.hust.constants.Interval;
import com.hust.constants.Media;
import com.hust.constants.Media.INFOTYPE;
import com.hust.constants.Media.MEDIALEVEL;
import com.hust.service.StatisticService;
import com.hust.util.Time;

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
    public Map<String, Integer> getIntervalCount(List<String> list, int interval) {
        Map<String, Integer> map = new TreeMap<String, Integer>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        if (null == list || 0 == list.size()) {
            return map;
        }
        switch (interval) {
            case Interval.DAY: {
                for (String time : list) {
                    if (!StringUtils.isBlank(time) && Time.isvalidate(time)) {
                        time = time.substring(5, 10);
                        if (null != map.get(time)) {
                            map.put(time, map.get(time) + 1);
                        } else {
                            map.put(time, 1);
                        }
                    }
                }

            }
                break;
            case Interval.HOUR: {
                for (String time : list) {
                    if ("" != time && null != time) {
                        time = time.substring(5, 13);
                        if (null != map.get(time)) {
                            map.put(time, map.get(time) + 1);
                        } else {
                            map.put(time, 1);
                        }
                    }
                }
            }
                break;
            case Interval.MONTH: {
                for (String time : list) {
                    if ("" != time && null != time) {
                        time = time.substring(0, 4);
                        if (null != map.get(time)) {
                            map.put(time, map.get(time) + 1);
                        } else {
                            map.put(time, 1);
                        }
                    }
                }
            }
                break;
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
            if (emotion.matches(Emotion.POSITIVE_REGEX)) {
                positive++;
            } else if (emotion.matches(Emotion.NEUTRAL_REGEX)) {
                negative++;
            } else if (emotion.matches(Emotion.NEGATIVE_REGEX)) {
                neutral++;
            }
        }
        map.put(Emotion.POSITIVE, positive);
        map.put(Emotion.NEUTRAL, neutral);
        map.put(Emotion.NEGATIVE, negative);
        return map;
    }

    @Override
    public Map<String, Integer> getInfoTypeCount(List<String> list) {
        // TODO Auto-generated method stub
        Map<String, Integer> map = new HashMap<String, Integer>();
        if (null == list || list.size() == 0) {
            return map;
        }
        int xinwen = 0, baozhi = 0, luntan = 0, wenda = 0, boke = 0, weixin = 0, tieba = 0, shouji = 0, shiping = 0,
                weibo = 0, weizhi = 0;
        for (String infotype : list) {
            switch (infotype) {
                case StringUtils.EMPTY: {
                    weizhi++;
                    break;
                }
                case INFOTYPE.XINWEN: {
                    xinwen++;
                    break;
                }
                case INFOTYPE.BAOZHI: {
                    baozhi++;
                    break;
                }
                case INFOTYPE.LUNTAN: {
                    luntan++;
                    break;
                }
                case INFOTYPE.WENDA: {
                    wenda++;
                    break;
                }
                case INFOTYPE.BOKE: {
                    boke++;
                    break;
                }
                case INFOTYPE.WEIXIN: {
                    weixin++;
                    break;
                }
                case INFOTYPE.TIEBA: {
                    tieba++;
                    break;
                }
                case INFOTYPE.SHOUJI: {
                    shouji++;
                    break;
                }
                case INFOTYPE.SHIPING: {
                    shiping++;
                    break;
                }
                case INFOTYPE.WEIBO: {
                    weibo++;
                    break;
                }
                default: {
                    weizhi++;
                    break;
                }
            }
        }
        map.put(INFOTYPE.XINWEN, xinwen);
        map.put(INFOTYPE.BAOZHI, baozhi);
        map.put(INFOTYPE.LUNTAN, luntan);
        map.put(INFOTYPE.WENDA, wenda);
        map.put(INFOTYPE.BOKE, boke);
        map.put(INFOTYPE.WEIXIN, weixin);
        map.put(INFOTYPE.TIEBA, tieba);
        map.put(INFOTYPE.SHOUJI, shouji);
        map.put(INFOTYPE.SHIPING, shiping);
        map.put(INFOTYPE.WEIBO, weibo);
        map.put(INFOTYPE.WEIZHI, weizhi);
        return map;
    }

    @Override
    public Map<String, Integer> getMediaLevelCount(List<String> list) {
        // TODO Auto-generated method stub
        if (null == list || list.size() == 0) {
            return null;
        }
        Map<String, Integer> map = new HashMap<String, Integer>();
        int zhongyang = 0, shengji = 0, qita = 0, weizhi = 0;
        for (String media : list) {
            String level = Media.getMediaLevelByName(media);
            switch (level) {
                case StringUtils.EMPTY: {
                    weizhi++;
                    break;
                }
                case MEDIALEVEL.ZHONGYANG: {
                    zhongyang++;
                    break;
                }
                case MEDIALEVEL.SHENGJI: {
                    shengji++;
                    break;
                }
                case MEDIALEVEL.QITA: {
                    qita++;
                    break;
                }
                default: {
                    weizhi++;
                    break;
                }
            }
        }
        map.put(MEDIALEVEL.ZHONGYANG, zhongyang);
        map.put(MEDIALEVEL.SHENGJI, shengji);
        map.put(MEDIALEVEL.QITA, qita);
        map.put(MEDIALEVEL.WEIZHI, weizhi++);
        return map;
    }

    @Override
    public Map<String, Integer> getNetizenAttention(List<String> list) {
        // TODO Auto-generated method stub
        Map<String, Integer> map = getInfoTypeCount(list);
        Map<String, Integer> weightMap = new HashMap<String, Integer>();
        if (null == map || map.size() == 0) {
            return weightMap;
        }
        Set<Entry<String, Integer>> infotype = map.entrySet();
        for (Entry<String, Integer> entry : infotype) {
            int weight = entry.getValue() * Media.getInfoTypeWeightByName(entry.getKey());
            weightMap.put(entry.getKey(), weight);
        }
        return weightMap;
    }

    @Override
    public Map<String, Integer> getMediaAttention(List<String> list) {
        // TODO Auto-generated method stub
        Map<String, Integer> map = getMediaLevelCount(list);
        Map<String, Integer> weightMap = new HashMap<String, Integer>();
        if (null == list || map.size() == 0) {
            return weightMap;
        }
        for (Entry<String, Integer> entry : map.entrySet()) {
            int weight = entry.getValue() * Media.getMediaWeightByName(entry.getKey());
            weightMap.put(entry.getKey(), weight);
        }
        return weightMap;
    }

}
