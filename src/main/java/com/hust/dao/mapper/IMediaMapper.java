package com.hust.dao.mapper;

import com.hust.model.IMedia;
import com.hust.model.IMediaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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