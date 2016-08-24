package com.hust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import com.hust.dao.mapper.IMediaMapper;
import com.hust.model.po.IMedia;
import com.hust.model.vo.IMediaExample;

public class IMediaDao {
    @Autowired
    private IMediaMapper iMediaMapper;

    long countByExample(IMediaExample example) {
        return iMediaMapper.countByExample(example);
    }

    int deleteByExample(IMediaExample example) {
        return iMediaMapper.deleteByExample(example);
    }

    int deleteByPrimaryKey(Integer id) {
        return iMediaMapper.deleteByPrimaryKey(id);
    }

    int insert(IMedia record) {
        return iMediaMapper.insert(record);
    }

    int insertSelective(IMedia record) {
        return iMediaMapper.insertSelective(record);
    }

    List<IMedia> selectByExample(IMediaExample example) {
        return iMediaMapper.selectByExample(example);
    }

    IMedia selectByPrimaryKey(Integer id) {
        return iMediaMapper.selectByPrimaryKey(id);
    }

    int updateByExampleSelective(@Param("record") IMedia record,
            @Param("example") IMediaExample example) {
        return iMediaMapper.updateByExampleSelective(record, example);
    }

    int updateByExample(@Param("record") IMedia record,
            @Param("example") IMediaExample example) {
        return iMediaMapper.updateByExample(record, example);
    }

    int updateByPrimaryKeySelective(IMedia record) {
        return iMediaMapper.updateByPrimaryKeySelective(record);
    }

    int updateByPrimaryKey(IMedia record) {
        return iMediaMapper.updateByPrimaryKey(record);
    }
}
