package com.homework.homework.service.impl;

import com.homework.homework.dao.BaseDao;
import com.homework.homework.entiry.User;
import com.homework.homework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017  七月  21  0021.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private BaseDao userDao;

    @Override
    public int insert(User user) {
        return userDao.insert(user);
    }

    @Override
    public int delete(Integer id) {
        return userDao.delete(id);
    }

    @Override
    public List<Map<String, Object>> getUser() {
        return userDao.getDatas();
    }

    @Override
    public int update(Integer id, User user) {
        return userDao.update(id, user);
    }
}
