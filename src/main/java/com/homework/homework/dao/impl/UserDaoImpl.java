package com.homework.homework.dao.impl;

import com.homework.homework.dao.BaseDao;
import com.homework.homework.entiry.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017  七月  24  0024.
 */
@Repository("userDao")
public class UserDaoImpl implements BaseDao<User> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insert(User user) {
        return jdbcTemplate.update("insert into yexugang_user(username,password) VALUE (?,?)", new Object[]{user.getUsername(), user.getPassword()});
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update("delete from yexugang_user where id=?", id);
    }

    @Override
    public List<Map<String, Object>> getDatas() {
        return jdbcTemplate.queryForList("select * from yexugang_user");
    }

    @Override
    public int update(Integer id, User user) {
        return jdbcTemplate.update("update yexugang_user set password=?,username=? where id=?", new Object[]{user.getUsername(), user.getPassword(), id});
    }
}
