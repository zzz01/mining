package com.hust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hust.dao.mapper.LMediaMapper;
import com.hust.model.po.LMedia;
import com.hust.model.vo.LMediaExample;

@Repository
public class LMediaDao {
    @Autowired
    private LMediaMapper lMediaMapper;

    public long countByExample(LMediaExample example) {
        return lMediaMapper.countByExample(example);
    }

    public int deleteByExample(LMediaExample example) {
        return lMediaMapper.deleteByExample(example);
    }

    public int deleteByPrimaryKey(Integer id) {
        return lMediaMapper.deleteByPrimaryKey(id);
    }

    public int insert(LMedia record) {
        return lMediaMapper.insert(record);
    }

    public int insertSelective(LMedia record) {
        return lMediaMapper.insertSelective(record);
    }

    public List<LMedia> selectByExample(LMediaExample example) {
        return lMediaMapper.selectByExample(example);
    }

    public LMedia selectByPrimaryKey(Integer id) {
        return lMediaMapper.selectByPrimaryKey(id);
    }

    public int updateByExampleSelective(@Param("record") LMedia record,
            @Param("example") LMediaExample example) {
        return lMediaMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(@Param("record") LMedia record,
            @Param("example") LMediaExample example) {
        return lMediaMapper.updateByExample(record, example);
    }

    public int updateByPrimaryKeySelective(LMedia record) {
        return lMediaMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(LMedia record) {
        return lMediaMapper.updateByPrimaryKey(record);
    }
}
