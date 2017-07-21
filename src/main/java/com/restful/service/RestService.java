package com.restful.service;

import com.restful.entiry.User;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017  七月  20  0020.
 */
public interface RestService {
    int delete(Integer id);

    int update(Integer id, User user);

    List<Map<String, Object>> get();

    int insert(User user);

    int[] insert(List<User> users);

    User getOne(Integer id);

    Map<String, Object> getMap(Integer id);
}
