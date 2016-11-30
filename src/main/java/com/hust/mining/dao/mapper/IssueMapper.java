package com.hust.mining.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.hust.mining.model.Issue;
import com.hust.mining.model.IssueExample;
import com.hust.mining.model.IssueWithBLOBs;

public interface IssueMapper {
    long countByExample(IssueExample example);

    int deleteByExample(IssueExample example);

    int deleteByPrimaryKey(String issueId);

    int insert(IssueWithBLOBs record);

    int insertSelective(IssueWithBLOBs record);

    List<IssueWithBLOBs> selectByExampleWithBLOBs(IssueExample example);

    List<Issue> selectByExample(IssueExample example);

    IssueWithBLOBs selectByPrimaryKey(String issueId);

    int updateByExampleSelective(@Param("record") IssueWithBLOBs record, @Param("example") IssueExample example);

    int updateByExampleWithBLOBs(@Param("record") IssueWithBLOBs record, @Param("example") IssueExample example);

    int updateByExample(@Param("record") Issue record, @Param("example") IssueExample example);

    int updateByPrimaryKeySelective(IssueWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(IssueWithBLOBs record);

    int updateByPrimaryKey(Issue record);
}