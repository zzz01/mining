package com.hust.util;

import org.apache.commons.lang.StringUtils;

import com.hust.constants.Constants;
import com.hust.constants.Emotion;
import com.hust.constants.Media.INFOTYPE;
import com.hust.constants.Media.MEDIALEVEL;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONCreator {

    public static JSONObject createTimeJson() {
        JSONObject json = new JSONObject();
        JSONObject emotionJson = JSONCreator.createEmotionJson();
        JSONObject infoTypeJson = JSONCreator.createInfoTypeJson();
        JSONObject mediaJson = JSONCreator.createMediaJson();
        JSONObject netizenAttenJson = JSONCreator.createInfoTypeJson();
        JSONObject mediaAttenJson = JSONCreator.createMediaJson();
        json.put(Constants.EMOTION_EN, emotionJson);
        json.put(Constants.INFOTYPE_EN, infoTypeJson);
        json.put(Constants.MEDIA_EN, mediaJson);
        json.put(Constants.NETIZENATTENTION_EN, netizenAttenJson);
        json.put(Constants.MEDIAATTENTION_EN, mediaAttenJson);
        return json;
    }

    public static JSONObject createEmotionJson() {
        JSONObject emotionJson = new JSONObject();
        emotionJson.put(Emotion.POSITIVE, 0);
        emotionJson.put(Emotion.NEUTRAL, 0);
        emotionJson.put(Emotion.NEGATIVE, 0);
        return emotionJson;
    }

    public static JSONObject createInfoTypeJson() {
        JSONObject infoTypeJson = new JSONObject();
        infoTypeJson.put(INFOTYPE.LUNTAN, 0);
        infoTypeJson.put(INFOTYPE.XINWEN, 0);
        infoTypeJson.put(INFOTYPE.BOKE, 0);
        infoTypeJson.put(INFOTYPE.BAOZHI, 0);
        infoTypeJson.put(INFOTYPE.WEIXIN, 0);
        infoTypeJson.put(INFOTYPE.TIEBA, 0);
        infoTypeJson.put(INFOTYPE.WENDA, 0);
        infoTypeJson.put(INFOTYPE.SHOUJI, 0);
        infoTypeJson.put(INFOTYPE.SHIPING, 0);
        infoTypeJson.put(INFOTYPE.WEIBO, 0);
        infoTypeJson.put(INFOTYPE.WEIZHI, 0);
        return infoTypeJson;
    }

    public static JSONObject createMediaJson() {
        JSONObject mediaJson = new JSONObject();
        mediaJson.put(MEDIALEVEL.ZHONGYANG, 0);
        mediaJson.put(MEDIALEVEL.SHENGJI, 0);
        mediaJson.put(MEDIALEVEL.QITA, 0);
        mediaJson.put(MEDIALEVEL.WEIZHI, 0);
        return mediaJson;
    }
    
    public static JSONObject createPaintProJson() {
        JSONObject json = new JSONObject();
        json.put(Constants.TITLE_EN, StringUtils.EMPTY);
        JSONArray categories = new JSONArray();
        JSONArray series = new JSONArray();
        json.put(Constants.CATEGORIES_EN, categories);
        json.put(Constants.SERIES_EN, series);
        return json;
    }
}
