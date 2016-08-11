package com.hust.util;

import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PaintUtil {

	public static JSONObject convertMap(Map<String, Integer> map) {
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
}
