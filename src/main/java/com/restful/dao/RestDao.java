package com.restful.dao;

import com.restful.entiry.User;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017  七月  20  0020.
 */
public interface RestDao {
    int delete(Integer id);

    int update(Integer id, User user);

    List<Map<String, Object>> get();

    int insert(User user);

    User getOne(Integer id);

    int[] batchInsert(List<User> user);

    Map<String, Object> getMap(Integer id);
}
