package com.hust.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.hust.dao.mapper.IssueInfoMapper;
import com.hust.model.IssueInfo;

public class IssueInfoDao {

    @Autowired
    private IssueInfoMapper issueInfoMapper;

    public int insert(IssueInfo issueInfo) {
        return issueInfoMapper.insert(issueInfo);
    }
}
