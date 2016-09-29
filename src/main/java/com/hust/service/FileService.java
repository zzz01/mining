package com.hust.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hust.model.Condition;
import com.hust.model.IssueFile;

public interface FileService {
    int insert(Condition con, HttpServletRequest request);

    int deleteById(String fileId);

    List<IssueFile> queryFilesByIssueId(String issueId);

    List<String[]> combineFilesContentOnSameIssueId(String issueId);
}
