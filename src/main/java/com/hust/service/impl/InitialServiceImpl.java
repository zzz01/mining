package com.hust.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hust.dao.IMediaDao;
import com.hust.dao.InfoTypeDao;
import com.hust.dao.LMediaDao;
import com.hust.model.po.IMedia;
import com.hust.model.po.InfoType;
import com.hust.model.po.LMedia;
import com.hust.model.vo.IMediaExample;
import com.hust.model.vo.InfoTypeExample;
import com.hust.model.vo.InfoTypeExample.Criteria;
import com.hust.model.vo.LMediaExample;
import com.hust.service.InitialService;

public class InitialServiceImpl implements InitialService {

    @Autowired
    private InfoTypeDao infoTypeDao;
    @Autowired
    private IMediaDao iMediaDao;
    @Autowired
    private LMediaDao lMediaDao;

    @Override
    public Map<String, String> getMedia() {
        Map<String, String> map = new HashMap<String, String>();
        IMediaExample iexample = new IMediaExample();
        iexample.createCriteria().andIdIsNotNull();
        List<IMedia> iMedialist = iMediaDao.selectByExample(iexample);
        for (IMedia iMedia : iMedialist) {
            map.put(iMedia.getName(), iMedia.getLevel());
        }
        return map;
    }

    @Override
    public Map<String, Integer> getLevel() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        LMediaExample lexample = new LMediaExample();
        lexample.createCriteria().andIdIsNotNull();
        List<LMedia> lmedialist = lMediaDao.selectByExample(lexample);
        for (LMedia lMedia : lmedialist) {
            map.put(lMedia.getName(), lMedia.getWeight());
        }
        return map;
    }

    @Override
    public Map<String, Integer> getInfoType() {
        // TODO Auto-generated method stub
        InfoTypeExample example = new InfoTypeExample();
        example.createCriteria().andIdIsNotNull();
        List<InfoType> list = infoTypeDao.selectByExample(example);
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (InfoType it : list) {
            map.put(it.getName(), it.getWeight());
        }
        return map;
    }

}
