package com.hust.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hust.model.po.IMedia;
import com.hust.model.vo.IMediaExample;

public interface IMediaMapper {
    long countByExample(IMediaExample example);

    int deleteByExample(IMediaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IMedia record);

    int insertSelective(IMedia record);

    List<IMedia> selectByExample(IMediaExample example);

    IMedia selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IMedia record, @Param("example") IMediaExample example);

    int updateByExample(@Param("record") IMedia record, @Param("example") IMediaExample example);

    int updateByPrimaryKeySelective(IMedia record);

    int updateByPrimaryKey(IMedia record);
}