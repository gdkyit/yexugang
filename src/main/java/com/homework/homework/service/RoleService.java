package com.homework.homework.service;

import com.homework.homework.entiry.Role;
import com.homework.homework.entiry.User;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017  七月  21  0021.
 */
public interface RoleService {
    int insert(Role role);

    int delete(Integer id);

    List<Map<String, Object>> getRole();

    int update(Integer id, Role role);
}
