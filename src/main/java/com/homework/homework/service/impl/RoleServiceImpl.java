package com.homework.homework.service.impl;

import com.homework.homework.dao.BaseDao;
import com.homework.homework.entiry.Role;
import com.homework.homework.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017  七月  21  0021.
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private BaseDao roleDao;

    @Override
    public int insert(Role role) {
        return roleDao.insert(role);
    }

    @Override
    public int delete(Integer id) {
        return roleDao.delete(id);
    }

    @Override
    public List<Map<String, Object>> getRole() {
        return roleDao.getDatas();
    }

    @Override
    public int update(Integer id, Role role) {
        return roleDao.update(id, role);
    }
}
