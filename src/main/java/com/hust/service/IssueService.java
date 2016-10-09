package com.hust.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hust.model.Issue;
import com.hust.model.IssueQueryCondition;
import com.hust.model.IssueWithBLOBs;

public interface IssueService {

    int createIssue(IssueWithBLOBs issue);

    int combineFiles(String UUID, String user);

    String getCurrentIssueId(HttpServletRequest request);

    List<Issue> queryIssue(IssueQueryCondition con);

    boolean combineOrigCountResult(int indexes, HttpServletRequest request);

    boolean deleteItemsFromModifiedClusterResult(int currentset, int[] indexes, HttpServletRequest request);

    IssueWithBLOBs getIssueById(String uuid);

    boolean deleteItemsFromOrigClusterResult(int currentset, int[] indexes, HttpServletRequest request);

    boolean combineModifiedCountResult(int[] indexes, HttpServletRequest request);

    int updateIssueInfo(IssueWithBLOBs issue, HttpServletRequest request);

    List<List<String[]>> queryClusterResult(String issueId);
   
    
    List<List<String[]>> queryModifiedClusterResult(String issueId);

    
    
}
