package com.homework.homework.dao.impl;

import com.homework.homework.dao.BaseDao;
import com.homework.homework.entiry.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017  七月  24  0024.
 */
@Repository("menuDao")
public class MenuDaoImpl implements BaseDao<Menu> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insert(Menu menu) {
        return jdbcTemplate.update("insert into yexugang_menu(menuname) VALUE (?)", new Object[]{menu.getMenuname()});

    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update("delete from yexugang_menu where id=?", id);

    }

    @Override
    public List<Map<String, Object>> getDatas() {
        return jdbcTemplate.queryForList("select * from yexugang_menu");
    }

    @Override
    public int update(Integer id, Menu menu) {
        return jdbcTemplate.update("update yexugang_menu set rolename=? where id=?", new Object[]{menu.getMenuname(), id});
    }
}
