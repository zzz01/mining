package com.hust.dao;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hust.dao.mapper.IssueMapper;
import com.hust.model.IssueExample;
import com.hust.model.IssueWithBLOBs;

public class IssueDao {

    @Autowired
    private IssueMapper issueMapper;

    public int insert(IssueWithBLOBs issueInfo) {
        return issueMapper.insert(issueInfo);
    }

    public int insertSelective(IssueWithBLOBs issueInfo) {
        return issueMapper.insertSelective(issueInfo);
    }

    public IssueWithBLOBs selectByUUID(String UUID) {
        IssueExample example = new IssueExample();
        example.createCriteria().andIssueIdEqualTo(UUID);
        return issueMapper.selectByExampleWithBLOBs(example).get(0);
    }

    public int updateIssueInfo(IssueWithBLOBs issue) {
        if (StringUtils.isBlank(issue.getIssueId())) {
            return 0;
        }
        String uuid = issue.getIssueId();
        IssueExample example = new IssueExample();
        example.createCriteria().andIssueIdEqualTo(uuid);
        return issueMapper.updateByExampleSelective(issue, example);
    }
}
