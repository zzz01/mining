package com.hust.dao;

import com.hust.dao.mapper.WeightMapper;
import com.hust.model.WeightExample;

public class WeightDao {

    private WeightMapper weightMapper;

    public int queryWeightByName(String name) {
        WeightExample example = new WeightExample();
        example.createCriteria().andNameEqualTo(name);
        return weightMapper.selectByExample(example).get(0).getWeight();
    }
}
