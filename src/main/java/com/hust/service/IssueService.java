package com.hust.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.hust.model.IssueInfoWithBLOBs;

public interface IssueService {

    List<String[]> readAndSave(MultipartFile file, String sourceType, HttpServletRequest request) throws Exception;

    IssueInfoWithBLOBs getByUUID(String UUID);

    int updateIssueInfo(IssueInfoWithBLOBs issue);
}
