package com.restful.dao.impl;

import com.restful.dao.RestDao;
import com.restful.entiry.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017  七月  20  0020.
 */
//  controller--service--dao--jdbctemplate
//常用update()--增，删，改,queryForList(),queryForObject(),queryForMap()--查
@Repository
public class RestDaoImpl implements RestDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;//用它来操作数据库

    /**
     * 删
     *
     * @param id
     * @return
     */
    @Override
    public int delete(Integer id) {
        //修改yxbz的值
        //return jdbcTemplate.update("delete from xugang where id=?", id);
        return jdbcTemplate.update("update  xugang set yxbz=? where id=?", 0, id);
    }

    /**
     * 更改
     *
     * @param id
     * @param user
     * @return
     */
    @Override
    public int update(Integer id, User user) {
        return jdbcTemplate.update("update xugang set title=? ,content=?,yxbz=? where id=?", user.getTitle(), user.getContent(), user.getYxbz(), id);
    }


    /**
     * 查
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> get() {
        //有多条数据有报错啊
        //return jdbcTemplate.queryForList("select * from xugang", User.class);
        return jdbcTemplate.queryForList("select * from xugang");
        /*return jdbcTemplate.query("select * from xugang", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setContent(resultSet.getString("content"));
                user.setTitle(resultSet.getString("title"));
                user.setTime(resultSet.getDate("time"));
                user.setYxbz(resultSet.getByte("yxbz"));
                return user;
            }
        });*/
    }

    /**
     * 插入(增加)
     *
     * @param user
     * @return
     */
    @Override
    public int insert(User user) {
        return jdbcTemplate.update("insert into xugang (title,content) VALUE (?,?)", new Object[]{user.getTitle(), user.getContent()});
    }


    /**
     * 获取某一个数据
     *
     * @param id
     * @return
     */
    @Override
    public User getOne(Integer id) {
        return jdbcTemplate.queryForObject("select * from xugang where id=?", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(id);
                user.setId(resultSet.getInt("id"));
                user.setContent(resultSet.getString("content"));
                user.setTitle(resultSet.getString("title"));
                user.setTime(resultSet.getDate("time"));
                user.setYxbz(resultSet.getByte("yxbz"));
                return user;
            }
        }, id);
    }


    /**
     * 批量
     *
     * @param user
     * @return
     */
    @Override
    public int[] batchInsert(List<User> user) {
        final List<User> lists = user;
        return jdbcTemplate.batchUpdate("insert into xugang(title,content) VALUE (?,?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setString(1, lists.get(i).getTitle());
                preparedStatement.setString(2, lists.get(i).getContent());
            }

            @Override
            public int getBatchSize() {
                return lists.size();
            }
        });
    }

    @Override
    public Map<String, Object> getMap(Integer id) {
        return jdbcTemplate.queryForMap("select * from xugang where id=?", id);
    }
}
