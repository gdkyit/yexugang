package com.todo.service.impl;

import com.todo.dao.TodoDao;
import com.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017  八月  17  0017.
 */
@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoDao todoDao;

    @Override
    public List<Map<String, Object>> getAll() {
        return todoDao.getAll();
    }

    @Override
    public List<Map<String, Object>> getActive() {
        return todoDao.getActive();
    }

    @Override
    public List<Map<String, Object>> getCompleted() {
        return todoDao.getCompleted();
    }

    @Override
    public Integer addTodo(String title) {
        return todoDao.addTodo(title);
    }

    @Override
    public Integer editTodo(Integer id, Integer completed) {
        return todoDao.editTodo(id, completed);
    }

    @Override
    public Integer deleteTodo(Integer id) {
        return todoDao.deleteTodo(id);
    }

    @Override
    public int[] deleteTodos(List<Map<String, Object>> params) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < params.size(); i++) {
            Map<String, Object> map = params.get(i);
            list.add((Integer) map.get("id"));
        }
        return todoDao.deleteTodos(list);
    }

    @Override
    public Integer editTodos(Integer completed) {
        return todoDao.editTodos(completed);
    }


}
