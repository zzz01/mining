package com.hust.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hust.dao.mapper.IssueInfoMapper;
import com.hust.model.IssueInfo;
import com.hust.model.IssueInfoExample;
import com.hust.model.IssueInfoWithBLOBs;

public class IssueInfoDao {

    @Autowired
    private IssueInfoMapper issueInfoMapper;

    public int insert(IssueInfoWithBLOBs issueInfo) {
        return issueInfoMapper.insert(issueInfo);
    }

    public int insertSelective(IssueInfoWithBLOBs issueInfo) {
        return issueInfoMapper.insertSelective(issueInfo);
    }

    public IssueInfoWithBLOBs selectByUUID(String UUID) {
        IssueInfoExample example = new IssueInfoExample();
        example.createCriteria().andIssueIdEqualTo(UUID);
        return issueInfoMapper.selectByExampleWithBLOBs(example).get(0);
    }

    public int updateIssueInfo(IssueInfoWithBLOBs issue) {
        if (StringUtils.isBlank(issue.getIssueId())) {
            return 0;
        }
        String uuid = issue.getIssueId();
        IssueInfoExample example = new IssueInfoExample();
        example.createCriteria().andIssueIdEqualTo(uuid);
        return issueInfoMapper.updateByExampleSelective(issue, example);
    }
}
