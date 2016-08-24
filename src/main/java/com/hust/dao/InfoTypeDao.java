package com.hust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import com.hust.dao.mapper.InfoTypeMapper;
import com.hust.model.po.InfoType;
import com.hust.model.vo.InfoTypeExample;

public class InfoTypeDao {

    @Autowired
    private InfoTypeMapper infoTypeMapper;

    long countByExample(InfoTypeExample example) {
        return infoTypeMapper.countByExample(example);
    }

    int deleteByExample(InfoTypeExample example) {
        return infoTypeMapper.deleteByExample(example);
    }

    int deleteByPrimaryKey(Integer id) {
        return infoTypeMapper.deleteByPrimaryKey(id);
    }

    int insert(InfoType record) {
        return infoTypeMapper.insert(record);
    }

    int insertSelective(InfoType record) {
        return infoTypeMapper.insertSelective(record);
    }

    List<InfoType> selectByExample(InfoTypeExample example) {
        return infoTypeMapper.selectByExample(example);
    }

    InfoType selectByPrimaryKey(Integer id) {
        return infoTypeMapper.selectByPrimaryKey(id);
    }

    int updateByExampleSelective(@Param("record") InfoType record,
            @Param("example") InfoTypeExample example) {
        return infoTypeMapper.updateByExampleSelective(record, example);
    }

    int updateByExample(@Param("record") InfoType record,
            @Param("example") InfoTypeExample example) {
        return infoTypeMapper.updateByExample(record, example);
    }

    int updateByPrimaryKeySelective(InfoType record) {
        return infoTypeMapper.updateByPrimaryKeySelective(record);
    }

    int updateByPrimaryKey(InfoType record) {
        return infoTypeMapper.updateByPrimaryKey(record);
    }
}
