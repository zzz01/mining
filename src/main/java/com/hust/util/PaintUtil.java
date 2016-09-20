package com.hust.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

import com.hust.constants.Constants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PaintUtil {

    public static JSONObject convertMap1(Map<String, Integer> map) {
        JSONObject json = new JSONObject();
        JSONArray xaxis = new JSONArray();
        JSONArray yaxis = new JSONArray();
        for (Entry<String, Integer> entry : map.entrySet()) {
            xaxis.add(entry.getKey());
            yaxis.add(entry.getValue());
        }
        json.put("xaxis", xaxis);
        json.put("yaxis", yaxis);
        return json;
    }

    public static JSONObject convertPaintJson(JSONObject json) {
        JSONObject paintJson = new JSONObject();
        if (null == json) {
            return paintJson;
        }
        JSONObject timelineJson = json.getJSONObject(Constants.TIMELINE_EN);
        JSONArray timeCategories = new JSONArray();
        List<String> times = new ArrayList<String>();
        for (Iterator timeIterator = timelineJson.keys(); timeIterator.hasNext();) {
            times.add(timeIterator.next().toString());
        }
        Collections.sort(times, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                // TODO Auto-generated method stub
                return o1.compareTo(o2);
            }
        });
        for (String timeKey : times) {
            JSONObject timeJson = timelineJson.getJSONObject(timeKey);
            timeCategories.add(timeKey);
            for (Iterator proIterator = timeJson.keys(); proIterator.hasNext();) {
                String proKey = proIterator.next().toString();
                JSONObject paintProJson = paintJson.getJSONObject(proKey);
                if (paintProJson.isNullObject()) {
                    paintProJson = JSONCreator.createPaintProJson();
                    paintJson.put(proKey, paintProJson);
                }
                JSONArray seriesJson = paintProJson.getJSONArray(Constants.SERIES_EN);
                JSONObject proJson = timeJson.getJSONObject(proKey);
                for (Iterator eleIterator = proJson.keys(); eleIterator.hasNext();) {
                    String eleKey = eleIterator.next().toString();
                    int eleValue = proJson.getInt(eleKey);
                    addToSeries(seriesJson, eleKey, eleValue);
                }
                paintJson.put(proKey, paintProJson);
            }
        }
        for (Iterator proIterator = paintJson.keys(); proIterator.hasNext();) {
            String key = proIterator.next().toString();
            JSONObject proJson = paintJson.getJSONObject(key);
            proJson.put(Constants.CATEGORIES_EN, timeCategories);
            proJson.put(Constants.TITLE_EN, keyENtoCH(key, json.getString(Constants.TITLE_EN)));
        }
        JSONObject countJson = json.getJSONObject(Constants.COUNT_EN);
        for (Iterator proIterator = countJson.keys(); proIterator.hasNext();) {
            String proKey = proIterator.next().toString();
            JSONObject proJson = countJson.getJSONObject(proKey);
            JSONObject paintProJson = JSONCreator.createPaintProJson();
            paintProJson.put(Constants.TITLE_EN, keyENtoCH(proKey, json.getString(Constants.TITLE_EN)));
            for (Iterator eleIterator = proJson.keys(); eleIterator.hasNext();) {
                String eleKey = eleIterator.next().toString();
                paintProJson.getJSONArray(Constants.CATEGORIES_EN).add(eleKey);
                int eleValue = proJson.getInt(eleKey);
                JSONArray serie = paintProJson.getJSONArray(Constants.SERIES_EN);
                if (serie.isEmpty()) {
                    JSONObject serieJson = new JSONObject();
                    serieJson.put(Constants.NAME_EN, Constants.SHULIANG_CH);
                    serieJson.put(Constants.DATA_EN, new JSONArray());
                    serie.add(serieJson);
                }
                paintProJson.put(Constants.SERIES_EN, serie);

                JSONArray data = ((JSONObject) paintProJson.getJSONArray(Constants.SERIES_EN).get(0))
                        .getJSONArray(Constants.DATA_EN);
                JSONArray eleArray = new JSONArray();
                eleArray.add(eleKey);
                eleArray.add(eleValue);
                data.add(eleArray);
            }
            paintJson.put(proKey, paintProJson);
        }
        return paintJson;
    }

    private static String keyENtoCH(String key, String issue) {
        if (StringUtils.isBlank(key)) {
            return StringUtils.EMPTY;
        }
        String title = "";
        switch (key) {
            case Constants.EMOTION_EN: {
                title = Constants.EMOTION_CH;
                break;
            }
            case Constants.INFOTYPE_EN: {
                title = Constants.INFOTYPE_CH;
                break;
            }
            case Constants.MEDIA_EN: {
                title = Constants.MEDIA_CH;
                break;
            }
            case Constants.MEDIAATTENTION_EN: {
                title = Constants.MEDIAATTENTION_CH;
                break;
            }
            case Constants.NETIZENATTENTION_EN: {
                title = Constants.NETIZENATTENTION_CH;
                break;
            }
        }
        return issue + "事件" + title + "趋势";
    }

    private static void addToSeries(JSONArray series, String name, int value) {
        if (series.isEmpty()) {
            JSONObject json = new JSONObject();
            JSONArray data = new JSONArray();
            json.put(Constants.NAME_EN, name);
            data.add(value);
            json.put(Constants.DATA_EN, data);
            series.add(json);
            return;
        }
        boolean find = false;
        for (int i = 0; i < series.size(); i++) {
            JSONObject json = series.getJSONObject(i);
            if (json.getString(Constants.NAME_EN).equals(name)) {
                json.getJSONArray(Constants.DATA_EN).add(value);
                find = true;
                break;
            }
        }
        if (!find) {
            JSONObject json = new JSONObject();
            JSONArray data = new JSONArray();
            json.put(Constants.NAME_EN, name);
            data.add(value);
            json.put(Constants.DATA_EN, data);
            series.add(json);
        }
    }
}
