package com.hust.dao.mapper;

import com.hust.model.po.InfoType;
import com.hust.model.vo.InfoTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InfoTypeMapper {
    long countByExample(InfoTypeExample example);

    int deleteByExample(InfoTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InfoType record);

    int insertSelective(InfoType record);

    List<InfoType> selectByExample(InfoTypeExample example);

    InfoType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InfoType record,
            @Param("example") InfoTypeExample example);

    int updateByExample(@Param("record") InfoType record,
            @Param("example") InfoTypeExample example);

    int updateByPrimaryKeySelective(InfoType record);

    int updateByPrimaryKey(InfoType record);
}