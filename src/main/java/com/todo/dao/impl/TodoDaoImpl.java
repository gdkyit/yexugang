package com.todo.dao.impl;

import com.mysql.jdbc.Statement;
import com.todo.dao.TodoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017  八月  17  0017.
 */
@Repository
public class TodoDaoImpl implements TodoDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getAll() {
        return jdbcTemplate.queryForList("select * from todo");
    }

    @Override
    public List<Map<String, Object>> getActive() {
        return jdbcTemplate.queryForList("select * from todo where completed = 1");
    }

    @Override
    public List<Map<String, Object>> getCompleted() {
        return jdbcTemplate.queryForList("select * from todo where completed = 0");
    }

    @Override
    public Integer addTodo(String title) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("insert into todo(title) VALUES (?)", new String[]{});
                ps = connection.prepareStatement("insert into todo(title) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, title);
                return ps;
            }
        }, keyHolder);
        //返回自增长id
        return keyHolder.getKey().intValue();
    }

    @Override
    public Integer editTodo(Integer id, Integer completed) {
        return jdbcTemplate.update("update todo set completed=? where id=?", completed, id);
    }

    @Override
    public Integer deleteTodo(Integer id) {
        return jdbcTemplate.update("delete from todo where id=?", id);
    }


    /*清空所有*/
    @Override
    public int[] deleteTodos(List<Integer> params) {
        return jdbcTemplate.batchUpdate("delete from todo where id=?", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setInt(1, (Integer) params.get(i));
            }

            @Override
            public int getBatchSize() {
                return params.size();
            }
        });
    }

    @Override
    public Integer editTodos(Integer completed) {
        return jdbcTemplate.update("update todo set completed =?", completed);
    }
}
