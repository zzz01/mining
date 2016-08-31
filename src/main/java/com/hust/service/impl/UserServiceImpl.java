package com.hust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hust.dao.UserDao;
import com.hust.model.User;
import com.hust.model.UserExample;
import com.hust.service.UserService;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean login(String username, String password) {
        UserExample example = new UserExample();
        example.createCriteria().andUserNameEqualTo(username).andPasswrodEqualTo(password);
        List<User> list = userDao.selectByExample(example);
        if (null == list || list.size() == 0) {
            return false;
        }
        return true;
    }

}
