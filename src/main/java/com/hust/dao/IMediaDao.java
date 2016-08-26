package com.hust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import com.hust.dao.mapper.IMediaMapper;
import com.hust.model.IMedia;
import com.hust.model.IMediaExample;

public class IMediaDao {
    @Autowired
    private IMediaMapper iMediaMapper;

    public long countByExample(IMediaExample example) {
        return iMediaMapper.countByExample(example);
    }

    public int deleteByExample(IMediaExample example) {
        return iMediaMapper.deleteByExample(example);
    }

    public int deleteByPrimaryKey(Integer id) {
        return iMediaMapper.deleteByPrimaryKey(id);
    }

    public int insert(IMedia record) {
        return iMediaMapper.insert(record);
    }

    public int insertSelective(IMedia record) {
        return iMediaMapper.insertSelective(record);
    }

    public List<IMedia> selectByExample(IMediaExample example) {
        return iMediaMapper.selectByExample(example);
    }

    public IMedia selectByPrimaryKey(Integer id) {
        return iMediaMapper.selectByPrimaryKey(id);
    }

    public int updateByExampleSelective(@Param("record") IMedia record,
            @Param("example") IMediaExample example) {
        return iMediaMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(@Param("record") IMedia record,
            @Param("example") IMediaExample example) {
        return iMediaMapper.updateByExample(record, example);
    }

    public int updateByPrimaryKeySelective(IMedia record) {
        return iMediaMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(IMedia record) {
        return iMediaMapper.updateByPrimaryKey(record);
    }
}
