package com.hust.dao;

import com.hust.dao.mapper.WebsiteMapper;
import com.hust.model.WebsiteExample;

public class WebsiteDao {

    private WebsiteMapper websiteMapper;

    public String queryLevelByUrl(String url) {
        WebsiteExample example = new WebsiteExample();
        example.createCriteria().andUrlEqualTo(url);
        return websiteMapper.selectByExample(example).get(0).getLevel();
    }

    public String queryTypeByUrl(String url) {
        WebsiteExample example = new WebsiteExample();
        example.createCriteria().andUrlEqualTo(url);
        return websiteMapper.selectByExample(example).get(0).getType();
    }
}
