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

    long countByExample(LMediaExample example) {
        return lMediaMapper.countByExample(example);
    }

    int deleteByExample(LMediaExample example) {
        return lMediaMapper.deleteByExample(example);
    }

    int deleteByPrimaryKey(Integer id) {
        return lMediaMapper.deleteByPrimaryKey(id);
    }

    int insert(LMedia record) {
        return lMediaMapper.insert(record);
    }

    int insertSelective(LMedia record) {
        return lMediaMapper.insertSelective(record);
    }

    List<LMedia> selectByExample(LMediaExample example) {
        return lMediaMapper.selectByExample(example);
    }

    LMedia selectByPrimaryKey(Integer id) {
        return lMediaMapper.selectByPrimaryKey(id);
    }

    int updateByExampleSelective(@Param("record") LMedia record,
            @Param("example") LMediaExample example) {
        return lMediaMapper.updateByExampleSelective(record, example);
    }

    int updateByExample(@Param("record") LMedia record,
            @Param("example") LMediaExample example) {
        return lMediaMapper.updateByExample(record, example);
    }

    int updateByPrimaryKeySelective(LMedia record) {
        return lMediaMapper.updateByPrimaryKeySelective(record);
    }

    int updateByPrimaryKey(LMedia record) {
        return lMediaMapper.updateByPrimaryKey(record);
    }
}
