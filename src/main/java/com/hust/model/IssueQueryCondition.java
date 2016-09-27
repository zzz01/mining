package com.hust.model;

import java.util.Date;

public class IssueQueryCondition {

    private String name;
    private Date startTime;
    private Date endTime;
    private String user;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "IssueQueryCondition [name=" + name + ", startTime=" + startTime + ", endTime=" + endTime + ", user="
                + user + "]";
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
