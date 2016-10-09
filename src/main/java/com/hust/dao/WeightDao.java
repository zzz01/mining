package com.hust.dao;

import java.util.List;

import com.hust.dao.mapper.WeightMapper;
import com.hust.model.Weight;
import com.hust.model.WeightExample;

public class WeightDao {

    private WeightMapper weightMapper;

    public int queryWeightByName(String name) {
        WeightExample example = new WeightExample();
        example.createCriteria().andNameEqualTo(name);
        List<Weight> list = weightMapper.selectByExample(example);
        if (null == list || list.size() == 0) {
            return 0;
        }
        return list.get(0).getWeight();
    }
}
