package com.hust.dao.mapper;

import com.hust.model.IssueInfo;
import com.hust.model.IssueInfoExample;
import com.hust.model.IssueInfoWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IssueInfoMapper {
    long countByExample(IssueInfoExample example);

    int deleteByExample(IssueInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(IssueInfoWithBLOBs record);

    int insertSelective(IssueInfoWithBLOBs record);

    List<IssueInfoWithBLOBs> selectByExampleWithBLOBs(IssueInfoExample example);

    List<IssueInfo> selectByExample(IssueInfoExample example);

    IssueInfoWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") IssueInfoWithBLOBs record, @Param("example") IssueInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") IssueInfoWithBLOBs record, @Param("example") IssueInfoExample example);

    int updateByExample(@Param("record") IssueInfo record, @Param("example") IssueInfoExample example);

    int updateByPrimaryKeySelective(IssueInfoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(IssueInfoWithBLOBs record);

    int updateByPrimaryKey(IssueInfo record);
}