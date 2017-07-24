package com.homework.homework.service;

import com.homework.homework.entiry.User;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017  七月  21  0021.
 */
public interface UserService {
    int insert(User user);

    int delete(Integer id);

    List<Map<String, Object>> getUser();

    int update(Integer id, User user);

}
