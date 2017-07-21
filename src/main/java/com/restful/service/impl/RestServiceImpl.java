package com.restful.service.impl;

import com.restful.dao.RestDao;
import com.restful.entiry.User;
import com.restful.service.RestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017  七月  20  0020.
 */
@Service
public class RestServiceImpl implements RestService {
    @Resource
    private RestDao restDao;


    @Override
    public int delete(Integer id) {
        return restDao.delete(id);
    }

    @Override
    public int update(Integer id, User user) {
        user.setTime(new Date());
        return restDao.update(id, user);
    }

    @Override
    public List<Map<String, Object>> get() {
        return restDao.get();
    }

    @Override
    public int insert(User user) {
        user.setTime(new Date());
        return restDao.insert(user);
    }

    @Override
    public int[] insert(List<User> users) {
        return restDao.batchInsert(users);
    }

    @Override
    public User getOne(Integer id) {
        return restDao.getOne(id);
    }

    @Override
    public Map<String, Object> getMap(Integer id) {
        return restDao.getMap(id);
    }
}
