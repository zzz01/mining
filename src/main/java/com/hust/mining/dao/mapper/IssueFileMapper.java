package com.hust.mining.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.hust.mining.model.IssueFile;
import com.hust.mining.model.IssueFileExample;

public interface IssueFileMapper {
    long countByExample(IssueFileExample example);

    int deleteByExample(IssueFileExample example);

    int deleteByPrimaryKey(String fileId);

    int insert(IssueFile record);

    int insertSelective(IssueFile record);

    List<IssueFile> selectByExampleWithBLOBs(IssueFileExample example);

    List<IssueFile> selectByExample(IssueFileExample example);

    IssueFile selectByPrimaryKey(String fileId);

    int updateByExampleSelective(@Param("record") IssueFile record, @Param("example") IssueFileExample example);

    int updateByExampleWithBLOBs(@Param("record") IssueFile record, @Param("example") IssueFileExample example);

    int updateByExample(@Param("record") IssueFile record, @Param("example") IssueFileExample example);

    int updateByPrimaryKeySelective(IssueFile record);

    int updateByPrimaryKeyWithBLOBs(IssueFile record);

    int updateByPrimaryKey(IssueFile record);
}