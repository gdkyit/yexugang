package com.homework.homework.service.impl;

import com.homework.homework.dao.BaseDao;
import com.homework.homework.entiry.Menu;
import com.homework.homework.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017  七月  21  0021.
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private BaseDao menuDao;

    @Override
    public int insert(Menu menu) {
        return menuDao.insert(menu);
    }

    @Override
    public int delete(Integer id) {
        return menuDao.delete(id);
    }

    @Override
    public List<Map<String, Object>> getMenu() {
        return menuDao.getDatas();
    }

    @Override
    public int update(Integer id, Menu menu) {
        return menuDao.update(id, menu);
    }
}
