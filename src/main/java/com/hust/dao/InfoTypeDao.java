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

    public long countByExample(InfoTypeExample example) {
        return infoTypeMapper.countByExample(example);
    }

    public int deleteByExample(InfoTypeExample example) {
        return infoTypeMapper.deleteByExample(example);
    }

    public int deleteByPrimaryKey(Integer id) {
        return infoTypeMapper.deleteByPrimaryKey(id);
    }

    public int insert(InfoType record) {
        return infoTypeMapper.insert(record);
    }

    public int insertSelective(InfoType record) {
        return infoTypeMapper.insertSelective(record);
    }

    public List<InfoType> selectByExample(InfoTypeExample example) {
        return infoTypeMapper.selectByExample(example);
    }

    public InfoType selectByPrimaryKey(Integer id) {
        return infoTypeMapper.selectByPrimaryKey(id);
    }

    public int updateByExampleSelective(@Param("record") InfoType record, @Param("example") InfoTypeExample example) {
        return infoTypeMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(@Param("record") InfoType record, @Param("example") InfoTypeExample example) {
        return infoTypeMapper.updateByExample(record, example);
    }

    public int updateByPrimaryKeySelective(InfoType record) {
        return infoTypeMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(InfoType record) {
        return infoTypeMapper.updateByPrimaryKey(record);
    }
}
