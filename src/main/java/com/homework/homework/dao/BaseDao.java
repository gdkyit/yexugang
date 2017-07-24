package com.homework.homework.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017  七月  24  0024.
 */
public interface BaseDao<T> {
    int delete(Integer id);

    int update(Integer id, T t);

    List<Map<String, Object>> getDatas();

    int insert(T t);
}
