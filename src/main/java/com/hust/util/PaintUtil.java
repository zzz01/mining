package com.hust.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

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
        paintJson.put(Constants.TITLE_STR, json.get(Constants.TITLE_STR));
        JSONObject timelineJson = json.getJSONObject(Constants.TIMELINE_STR);
        JSONArray categories = new JSONArray();
        JSONArray series = new JSONArray();
        for (Iterator timelineIterator = timelineJson.keys(); timelineIterator.hasNext();) {
            String key = timelineIterator.next().toString();
            JSONObject timeJson = timelineJson.getJSONObject(key);
            categories.add(key);
            
        }
        return null;
    }
}
