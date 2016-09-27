package com.hust.model;

public class IssueWithBLOBs extends Issue {
    private byte[] filteredContent;

    private byte[] clusterResult;

    private byte[] modifiedClusterResult;

    private byte[] origCountResult;

    private byte[] modifiedOrigCountResult;

    private byte[] statisticResult;

    public byte[] getFilteredContent() {
        return filteredContent;
    }

    public void setFilteredContent(byte[] filteredContent) {
        this.filteredContent = filteredContent;
    }

    public byte[] getClusterResult() {
        return clusterResult;
    }

    public void setClusterResult(byte[] clusterResult) {
        this.clusterResult = clusterResult;
    }

    public byte[] getModifiedClusterResult() {
        return modifiedClusterResult;
    }

    public void setModifiedClusterResult(byte[] modifiedClusterResult) {
        this.modifiedClusterResult = modifiedClusterResult;
    }

    public byte[] getOrigCountResult() {
        return origCountResult;
    }

    public void setOrigCountResult(byte[] origCountResult) {
        this.origCountResult = origCountResult;
    }

    public byte[] getModifiedOrigCountResult() {
        return modifiedOrigCountResult;
    }

    public void setModifiedOrigCountResult(byte[] modifiedOrigCountResult) {
        this.modifiedOrigCountResult = modifiedOrigCountResult;
    }

    public byte[] getStatisticResult() {
        return statisticResult;
    }

    public void setStatisticResult(byte[] statisticResult) {
        this.statisticResult = statisticResult;
    }
}