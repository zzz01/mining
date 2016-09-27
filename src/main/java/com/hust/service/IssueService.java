package com.hust.service;

import javax.servlet.http.HttpServletRequest;

import com.hust.model.IssueWithBLOBs;

public interface IssueService {

    int createIssue(IssueWithBLOBs issue);

    IssueWithBLOBs getById(String UUID);

    int updateIssueInfo(IssueWithBLOBs issue);

    int combineFiles(String UUID, String user);

    String getCurrentIssueId(HttpServletRequest request);
}
