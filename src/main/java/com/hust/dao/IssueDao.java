package com.hust.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hust.dao.mapper.IssueMapper;
import com.hust.model.Issue;
import com.hust.model.IssueExample;
import com.hust.model.IssueExample.Criteria;
import com.hust.model.IssueQueryCondition;
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
        return issueMapper.selectByPrimaryKey(UUID);
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

    public List<Issue> queryIssue(IssueQueryCondition con) {
        IssueExample example = new IssueExample();
        Criteria criteria = example.createCriteria();
        if (!StringUtils.isBlank(con.getUser())) {
            criteria.andCreatorEqualTo(con.getUser());
        }
        if (!StringUtils.isBlank(con.getIssueName())) {
            criteria.andIssueNameEqualTo(con.getIssueName());
        }
        if (null != con.getCreateStartTime()) {
            criteria.andCreateTimeGreaterThanOrEqualTo(con.getCreateStartTime());
        }
        if (null != con.getCreateEndTime()) {
            criteria.andCreateTimeLessThanOrEqualTo(con.getCreateEndTime());
        }
        if (null != con.getLastUpdateStartTime()) {
            criteria.andLastUpdateTimeGreaterThanOrEqualTo(con.getLastUpdateStartTime());
        }
        if (null != con.getLastUpdateEndTime()) {
            criteria.andLastUpdateTimeLessThanOrEqualTo(con.getLastUpdateEndTime());
        }
        example.setOrderByClause("last_update_time desc");
        example.setStart(con.getPageNo() - 1);
        example.setLimit(con.getPageSize());
        return issueMapper.selectByExample(example);
    }
    
    public long countIssues(IssueQueryCondition con){
        IssueExample example = new IssueExample();
        Criteria criteria = example.createCriteria();
        if (!StringUtils.isBlank(con.getUser())) {
            criteria.andCreatorEqualTo(con.getUser());
        }
        if (!StringUtils.isBlank(con.getIssueName())) {
            criteria.andIssueNameEqualTo(con.getIssueName());
        }
        if (null != con.getCreateStartTime()) {
            criteria.andCreateTimeGreaterThanOrEqualTo(con.getCreateStartTime());
        }
        if (null != con.getCreateEndTime()) {
            criteria.andCreateTimeLessThanOrEqualTo(con.getCreateEndTime());
        }
        if (null != con.getLastUpdateStartTime()) {
            criteria.andLastUpdateTimeGreaterThanOrEqualTo(con.getLastUpdateStartTime());
        }
        if (null != con.getLastUpdateEndTime()) {
            criteria.andLastUpdateTimeLessThanOrEqualTo(con.getLastUpdateEndTime());
        }
        example.setOrderByClause("last_update_time desc");
        return issueMapper.countByExample(example);
    }
}
