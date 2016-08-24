package com.hust.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hust.dao.IMediaDao;
import com.hust.dao.InfoTypeDao;
import com.hust.dao.InitialDao;
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
    private InitialDao initialDao;
    @Autowired
    private InfoTypeDao infoTypeDao;
    @Autowired
    private IMediaDao iMediaDao;
    @Autowired
    private LMediaDao lMediaDao;

    @Override
    public Map<String, String> getMedia() {
        Map<String, String> map = new HashMap<String, String>();
        LMediaExample lexample = new LMediaExample();
        LMediaExample.Criteria lcriteria = lexample.createCriteria();
        lcriteria.andIdIsNotNull();
        List<LMedia> lmedialist = lMediaDao.selectByExample(lexample);
        for (LMedia lMedia : lmedialist) {
            IMediaExample iexample = new IMediaExample();
            IMediaExample.Criteria icriteria = iexample.createCriteria();
            icriteria.andLevelEqualTo(lMedia.getName());
            List<IMedia> iMedialist = iMediaDao.selectByExample(iexample);
            for (IMedia iMedia : iMedialist) {
                map.put(iMedia.getName(), iMedia.getLevel());
            }
        }
        return map;
    }

    @Override
    public List<InfoType> getInfoType() {
        // TODO Auto-generated method stub
        InfoTypeExample example = new InfoTypeExample();
        Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        return infoTypeDao.selectByExample(example);
    }

}
