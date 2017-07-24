package com.homework.homework.dao.impl;

import com.homework.homework.dao.BaseDao;
import com.homework.homework.entiry.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017  七月  24  0024.
 */
@Repository("roleDao")
public class RoleDaoImpl implements BaseDao<Role> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insert(Role role) {
        return jdbcTemplate.update("insert into yexugang_role(rolename) VALUES (?)", new Object[]{role.getRolename()});
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update("delete from yexugang_role where id=?", id);
    }

    @Override
    public List<Map<String, Object>> getDatas() {
        return jdbcTemplate.queryForList("select * from yexugang_role");
    }

    @Override
    public int update(Integer id, Role role) {
        return jdbcTemplate.update("update yexugang_role set rolename where id=?", new Object[]{role.getRolename(), id});
    }
}
