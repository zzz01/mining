package com.hust.mining.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hust.mining.dao.mapper.IssueFileMapper;
import com.hust.mining.model.IssueFile;
import com.hust.mining.model.IssueFileExample;

public class FileDao {

    @Autowired
    private IssueFileMapper issueFileMapper;

    public int insert(IssueFile file) {
        return issueFileMapper.insert(file);
    }

    public int deleteById(String fileId) {
        return issueFileMapper.deleteByPrimaryKey(fileId);
    }

    public List<IssueFile> queryFilesByIssueId(String issueId) {
        IssueFileExample example = new IssueFileExample();
        example.createCriteria().andIssueIdEqualTo(issueId);
        return issueFileMapper.selectByExampleWithBLOBs(example);
    }
}
