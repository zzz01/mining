package com.hust.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hust.constants.Constants;
import com.hust.constants.Emotion;
import com.hust.constants.Interval;
import com.hust.constants.Media;
import com.hust.constants.Media.INFOTYPE;
import com.hust.constants.Media.MEDIALEVEL;
import com.hust.model.StatisticCondition;
import com.hust.service.StatisticService;
import com.hust.util.JSONCreator;
import com.hust.util.Time;

import net.sf.json.JSONObject;

@Service
public class StatisticServiceImpl implements StatisticService {
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(StatisticServiceImpl.class);

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
    public Map<String, Integer> getNetizenAttention(Map<String, Integer> map) {
        // TODO Auto-generated method stub
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

    @Override
    public JSONObject statistic(StatisticCondition sc) {
        // TODO Auto-generated method stub
        List<String[]> list = sc.getList();
        JSONObject json = new JSONObject();
        if (null == list || list.size() == 0) {
            json.put("msg", "error");
            return json;
        }
        JSONObject timelineJson = new JSONObject();
        for (int i = 0; i < list.size() - 1; i++) {
            String[] array = list.get(i);
            if (isEmptyArray(array)) {
                continue;
            }
            String timeKey = getTimeKey(array[sc.getTimeIndex()], sc.getInterval());
            putValue(timelineJson, timeKey, Constants.EMOTION_EN, array[sc.getEmotionIndex()]);
            putValue(timelineJson, timeKey, Constants.INFOTYPE_EN, array[sc.getInfoTypeIndex()]);
            putValue(timelineJson, timeKey, Constants.MEDIA_EN, Media.getMediaLevelByName(array[sc.getMediaIndex()]));
        }
        json.put(Constants.TIMELINE_EN, timelineJson);
        calculateNetizenAttention(json);
        calculateMediaAttention(json);
        calculateCount(json);
        json.put("msg", "success");
        logger.info("统计结果：" + json.toString());
        return json;
    }

    private boolean isEmptyArray(String[] array) {
        if (null == array || array.length == 0) {
            return true;
        }
        for (int i = 0; i < array.length / 2; i++) {
            if (!StringUtils.isBlank(array[i])) {
                return false;
            }
        }
        return true;
    }

    private void calculateCount(JSONObject json) {
        if (null == json) {
            return;
        }
        JSONObject timelineJson = json.getJSONObject(Constants.TIMELINE_EN);
        JSONObject mediaCount = new JSONObject();
        JSONObject infoTypeCount = new JSONObject();
        JSONObject emotionCount = new JSONObject();
        for (Iterator iterator = timelineJson.keys(); iterator.hasNext();) {
            JSONObject timeJson = timelineJson.getJSONObject(iterator.next().toString());
            JSONObject mediaJson = timeJson.getJSONObject(Constants.MEDIA_EN);
            for (Iterator mediaIterator = mediaJson.keys(); mediaIterator.hasNext();) {
                String key = mediaIterator.next().toString();
                int value = mediaJson.getInt(key);
                try {
                    int oldValue = mediaCount.getInt(key);
                    mediaCount.put(key, oldValue + value);
                } catch (Exception e) {
                    mediaCount.put(key, value);
                }
            }
            JSONObject infoTypeJson = timeJson.getJSONObject(Constants.INFOTYPE_EN);
            for (Iterator infoTypeIterator = infoTypeJson.keys(); infoTypeIterator.hasNext();) {
                String key = infoTypeIterator.next().toString();
                int value = infoTypeJson.getInt(key);
                try {
                    int oldValue = infoTypeCount.getInt(key);
                    infoTypeCount.put(key, oldValue + value);
                } catch (Exception e) {
                    infoTypeCount.put(key, value);
                }
            }
            JSONObject emotionJson = timeJson.getJSONObject(Constants.EMOTION_EN);
            for (Iterator emotionIterator = emotionJson.keys(); emotionIterator.hasNext();) {
                String key = emotionIterator.next().toString();
                int value = emotionJson.getInt(key);
                try {
                    int oldValue = emotionCount.getInt(key);
                    emotionCount.put(key, oldValue + value);
                } catch (Exception e) {
                    emotionCount.put(key, value);
                }
            }
        }
        JSONObject countJson = new JSONObject();
        countJson.put(Constants.MEDIA_COUNT_EN, mediaCount);
        countJson.put(Constants.INFOTYPE_COUNT_EN, infoTypeCount);
        countJson.put(Constants.EMOTION_COUNT_EN, emotionCount);
        json.put(Constants.COUNT_EN, countJson);
    }

    @SuppressWarnings("rawtypes")
    private void calculateMediaAttention(JSONObject json) {
        if (null == json) {
            return;
        }
        JSONObject timelineJson = json.getJSONObject(Constants.TIMELINE_EN);
        for (Iterator iterator = timelineJson.keys(); iterator.hasNext();) {
            JSONObject timeJson = timelineJson.getJSONObject(iterator.next().toString());
            JSONObject mediaJson = timeJson.getJSONObject(Constants.MEDIA_EN);
            JSONObject mediaAttenJson = new JSONObject();
            for (Iterator mediaIterator = mediaJson.keys(); mediaIterator.hasNext();) {
                String key = mediaIterator.next().toString();
                int value = mediaJson.getInt(key);
                int attention = value * Media.getLevelWeightByName(key);
                mediaAttenJson.put(key, attention);
            }
            timeJson.put(Constants.MEDIAATTENTION_EN, mediaAttenJson);
        }
    }

    @SuppressWarnings("rawtypes")
    private void calculateNetizenAttention(JSONObject json) {
        if (null == json) {
            return;
        }
        JSONObject timelineJson = json.getJSONObject(Constants.TIMELINE_EN);
        for (Iterator iterator = timelineJson.keys(); iterator.hasNext();) {
            JSONObject timeJson = timelineJson.getJSONObject(iterator.next().toString());
            JSONObject infoTypeJson = timeJson.getJSONObject(Constants.INFOTYPE_EN);
            JSONObject netizenAttenJson = new JSONObject();
            for (Iterator infoIterator = infoTypeJson.keys(); infoIterator.hasNext();) {
                String key = infoIterator.next().toString();
                int value = infoTypeJson.getInt(key);
                int attention = value * Media.getInfoTypeWeightByName(key);
                netizenAttenJson.put(key, attention);
            }
            timeJson.put(Constants.NETIZENATTENTION_EN, netizenAttenJson);
        }
    }

    private void putValue(JSONObject json, String timeKey, String proKey, String elemKey) {
        JSONObject timeJson = json.getJSONObject(timeKey);
        if (timeJson.isNullObject()) {
            timeJson = JSONCreator.createTimeJson();
            json.put(timeKey, timeJson);
        }
        JSONObject proJson = timeJson.getJSONObject(proKey);
        try {
            int value = proJson.getInt(elemKey);
            proJson.put(elemKey, value + 1);
        } catch (Exception e) {
            proJson.put(Constants.WEIZHI_CH, 1);
        }
    }

    private String getTimeKey(String time, int interval) {
        if (StringUtils.isBlank(time) || !Time.isvalidate(time)) {
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

}
