package com.hust.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

import com.hust.constants.Constants;
import com.sun.tools.javac.code.Attribute.Constant;

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

    public static JSONObject convertMap(Map<String, Object> map) {

        return null;
    }

    public static JSONObject convertPaintPie(JSONObject json) {

        return null;
    }

    public static JSONObject convertPaintLine(JSONObject json) {
        JSONObject paintJson = new JSONObject();
        if (null == json) {
            return paintJson;
        }
        JSONObject timelineJson = json.getJSONObject(Constants.TIMELINE_EN);
        JSONArray categories = new JSONArray();
        for (Iterator timeIterator = timelineJson.keys(); timeIterator.hasNext();) {
            String timeKey = timeIterator.next().toString();
            JSONObject timeJson = timelineJson.getJSONObject(timeKey);
            categories.add(timeKey);
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
            proJson.put(Constants.CATEGORIES_EN, categories);
            proJson.put(Constants.TITLE_EN, json.getString(Constants.TITLE_EN));
        }
        return paintJson;
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
