package com.hust.model;

public class Condition {
    private int targetIndex;
    private int timeIndex;
    private int mediaIndex;
    private int emotionIndex;
    private int infoTypeIndex;
    private int interval;

    public int getTargetIndex() {
        return targetIndex;
    }

    public void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    public int getTimeIndex() {
        return timeIndex;
    }

    public void setTimeIndex(int timeIndex) {
        this.timeIndex = timeIndex;
    }

    public int getMediaIndex() {
        return mediaIndex;
    }

    public void setMediaIndex(int mediaIndex) {
        this.mediaIndex = mediaIndex;
    }

    public int getEmotionIndex() {
        return emotionIndex;
    }

    public void setEmotionIndex(int emotionIndex) {
        this.emotionIndex = emotionIndex;
    }

    public int getInfoTypeIndex() {
        return infoTypeIndex;
    }

    public void setInfoTypeIndex(int infoTypeIndex) {
        this.infoTypeIndex = infoTypeIndex;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    @Override
    public String toString() {
        return "Condition [targetIndex=" + targetIndex + ", timeIndex=" + timeIndex + ", mediaIndex=" + mediaIndex
                + ", emotionIndex=" + emotionIndex + ", infoTypeIndex=" + infoTypeIndex + ", interval=" + interval
                + "]";
    }

}
