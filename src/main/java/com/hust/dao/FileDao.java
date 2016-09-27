package com.hust.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hust.dao.mapper.IssueFileMapper;
import com.hust.model.IssueFile;
import com.hust.model.IssueFileExample;
import com.hust.util.ConvertUtil;

public class FileDao {

    @Autowired
    private IssueFileMapper issueFileMapper;

    public int insert(IssueFile file) {
        return issueFileMapper.insert(file);
    }

    public int deleteById(String fileId) {
        return issueFileMapper.deleteByPrimaryKey(fileId);
    }

    public List<String[]> queryContents(String issueId) throws Exception {
        IssueFileExample example = new IssueFileExample();
        example.createCriteria().andIssueIdEqualTo(issueId);
        List<IssueFile> files = issueFileMapper.selectByExample(example);
        List<String[]> list = new ArrayList<String[]>();
        for (IssueFile file : files) {
            List<String[]> content = (List<String[]>) ConvertUtil.convertBytesToObject(file.getContent());
            list.addAll(content);
        }
        return list;
    }

    public List<IssueFile> queryFiles(String issueId) {
        IssueFileExample example = new IssueFileExample();
        example.createCriteria().andIssueIdEqualTo(issueId);
        return issueFileMapper.selectByExample(example);
    }
}
