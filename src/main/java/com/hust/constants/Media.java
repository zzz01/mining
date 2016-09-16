package com.hust.constants;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hust.service.InitialService;

public class Media {

    public static final Map<String, Integer> LEVEL = new HashMap<String, Integer>();

    public static final Map<String, String> MEDIA = new HashMap<String, String>();

    public static final Map<String, Integer> TYPE = new HashMap<String, Integer>();

    @Autowired
    public InitialService initialService;

    @PostConstruct
    public void init() {
        // 初始化信息类型
        Map<String, Integer> infotype = initialService.getInfoType();
        for (Entry<String, Integer> entry : infotype.entrySet()) {
            TYPE.put(entry.getKey(), entry.getValue());
        }

        // 初始化媒体表
        Map<String, String> media = initialService.getMedia();
        for (Entry<String, String> entry : media.entrySet()) {
            MEDIA.put(entry.getKey(), entry.getValue());
        }

        // 初始化媒体级别
        Map<String, Integer> level = initialService.getLevel();
        for (Entry<String, Integer> entry : level.entrySet()) {
            LEVEL.put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * 通过信息类型获取类型的权重
     * 
     * @param name
     * @return
     */
    public static int getInfoTypeWeightByName(String name) {
        Integer weight = TYPE.get(name);
        if (null == weight) {
            return 0;
        }
        return weight;
    }

    public static int getMediaWeightByName(String name) {
        String level = getMediaLevelByName(name);
        if (null == level || StringUtils.isBlank(level)) {
            return 0;
        }
        Integer weight = LEVEL.get(level);
        if (null == weight) {
            return 0;
        }
        return weight;
    }

    public static int getLevelWeightByName(String level) {
        Integer weight = LEVEL.get(level);
        if (null == weight) {
            return 0;
        }
        return weight;
    }

    public static String getMediaLevelByName(String name) {
        String level = MEDIA.get(name);
        if (null == level) {
            return "未知";
        }
        return level;
    }

    // 媒体级别
    public static class MEDIALEVEL {
        public static final String ZHONGYANG = "中央";
        public static final String SHENGJI = "省级";
        public static final String QITA = "其他新闻网站";
        public static final String WEIZHI = "未知";
    }

    public static class INFOTYPE {
        public static final String XINWEN = "新闻";
        public static final String BAOZHI = "报纸";
        public static final String LUNTAN = "论坛";
        public static final String WENDA = "问答";
        public static final String BOKE = "博客";
        public static final String WEIXIN = "微信";
        public static final String TIEBA = "贴吧";
        public static final String SHOUJI = "手机";
        public static final String SHIPING = "视频";
        public static final String WEIBO = "微博";
        public static final String WEIZHI = "未知";
    }
}
