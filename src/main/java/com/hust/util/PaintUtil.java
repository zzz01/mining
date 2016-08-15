package com.hust.util;

import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PaintUtil {

	public static JSONObject convertMapToXY(Map<String, Integer> map, String name) {
		JSONObject json = new JSONObject();
		JSONArray xaixs = new JSONArray();
		JSONObject yaixs = new JSONObject();
		JSONArray yset = new JSONArray();
		for (Entry<String, Integer> entry : map.entrySet()) {
			xaixs.add(entry.getKey());
			yset.add(entry.getValue());
		}
		yaixs.put("name", name);
		yaixs.put("data", yset);
		json.put("xaixs", xaixs);
		json.put("yaixs", yaixs);
		return json;
	}

	public static JSONObject packageXYJson(JSONObject... jsons) {
		JSONObject result = new JSONObject();
		JSONArray array = new JSONArray();
		for (JSONObject json : jsons) {
			array.add(json.get("yaixs"));
		}
		result.put("xaixs", jsons[0].get("xaixs"));
		result.put("yaixs", array);
		return result;
	}

	public static JSONArray convertMapToPie(Map<String, Integer> map) {
		JSONArray array = new JSONArray();
		for (Entry<String, Integer> entry : map.entrySet()) {
			JSONArray temp = new JSONArray();
			temp.add(entry.getKey());
			temp.add(entry.getValue());
			array.add(temp);
		}
		return array;
	}
}
