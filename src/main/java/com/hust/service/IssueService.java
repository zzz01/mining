package com.hust.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hust.model.Issue;
import com.hust.model.IssueQueryCondition;
import com.hust.model.IssueWithBLOBs;

public interface IssueService {

    int createIssue(IssueWithBLOBs issue);

    IssueWithBLOBs getById(String UUID);

    int updateIssueInfo(IssueWithBLOBs issue);

    int combineFiles(String UUID, String user);

    String getCurrentIssueId(HttpServletRequest request);

    List<Issue> queryIssue(IssueQueryCondition con);
}
