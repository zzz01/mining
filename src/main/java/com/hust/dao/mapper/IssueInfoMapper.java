package com.hust.dao.mapper;

import com.hust.model.IssueInfo;
import com.hust.model.IssueInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IssueInfoMapper {
    long countByExample(IssueInfoExample example);

    int deleteByExample(IssueInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(IssueInfo record);

    int insertSelective(IssueInfo record);

    List<IssueInfo> selectByExample(IssueInfoExample example);

    IssueInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") IssueInfo record, @Param("example") IssueInfoExample example);

    int updateByExample(@Param("record") IssueInfo record, @Param("example") IssueInfoExample example);

    int updateByPrimaryKeySelective(IssueInfo record);

    int updateByPrimaryKey(IssueInfo record);
}