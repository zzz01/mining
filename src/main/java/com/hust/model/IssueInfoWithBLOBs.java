package com.hust.model;

public class IssueInfoWithBLOBs extends IssueInfo {
    private byte[] content;

    private byte[] resultList;

    private byte[] resultJson;

    private byte[] paintJson;

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public byte[] getResultList() {
        return resultList;
    }

    public void setResultList(byte[] resultList) {
        this.resultList = resultList;
    }

    public byte[] getResultJson() {
        return resultJson;
    }

    public void setResultJson(byte[] resultJson) {
        this.resultJson = resultJson;
    }

    public byte[] getPaintJson() {
        return paintJson;
    }

    public void setPaintJson(byte[] paintJson) {
        this.paintJson = paintJson;
    }
}