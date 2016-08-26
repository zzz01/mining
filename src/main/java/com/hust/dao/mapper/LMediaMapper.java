package com.hust.dao.mapper;

import com.hust.model.LMedia;
import com.hust.model.LMediaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LMediaMapper {
    long countByExample(LMediaExample example);

    int deleteByExample(LMediaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LMedia record);

    int insertSelective(LMedia record);

    List<LMedia> selectByExample(LMediaExample example);

    LMedia selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LMedia record, @Param("example") LMediaExample example);

    int updateByExample(@Param("record") LMedia record, @Param("example") LMediaExample example);

    int updateByPrimaryKeySelective(LMedia record);

    int updateByPrimaryKey(LMedia record);
}