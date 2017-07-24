package com.homework.homework.service;

import com.homework.homework.entiry.Menu;
import com.homework.homework.entiry.User;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017  七月  21  0021.
 */
public interface MenuService {
    int insert(Menu menu);

    int delete(Integer id);

    List<Map<String, Object>> getMenu();

    int update(Integer id, Menu menu);
}
