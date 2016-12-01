package com.hust.mining.model.params;

import java.util.List;

public class StatisticCondition {

    private List<String[]> list;
    private int timeIndex;
    private int titleIndex;
    private int emotionIndex;
    private int infoTypeIndex;
    private int mediaIndex;
    private int interval;

    public int getTimeIndex() {
        return timeIndex;
    }

    public void setTimeIndex(int timeIndex) {
        this.timeIndex = timeIndex;
    }

    public int getTitleIndex() {
        return titleIndex;
    }

    public void setTitleIndex(int titleIndex) {
        this.titleIndex = titleIndex;
    }

    public int getEmotionIndex() {
        return emotionIndex;
    }

    public void setEmotionIndex(int emotionIndex) {
        this.emotionIndex = emotionIndex;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public List<String[]> getList() {
        return list;
    }

    public void setList(List<String[]> list) {
        this.list = list;
    }

    public int getInfoTypeIndex() {
        return infoTypeIndex;
    }

    public void setInfoTypeIndex(int infoTypeIndex) {
        this.infoTypeIndex = infoTypeIndex;
    }

    public int getMediaIndex() {
        return mediaIndex;
    }

    public void setMediaIndex(int mediaIndex) {
        this.mediaIndex = mediaIndex;
    }

    @Override
    public String toString() {
        return "StatisticCondition [list=" + list + ", timeIndex=" + timeIndex + ", titleIndex=" + titleIndex
                + ", emotionIndex=" + emotionIndex + ", infoTypeIndex=" + infoTypeIndex + ", mediaIndex=" + mediaIndex
                + ", interval=" + interval + "]";
    }

}
