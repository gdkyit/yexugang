package com.todo.service;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017  八月  17  0017.
 */
public interface TodoService {
    List<Map<String,Object>> getAll();
    List<Map<String,Object>> getActive();
    List<Map<String,Object>> getCompleted();
    Integer addTodo(String title);
    Integer editTodo(Integer id,Integer completed);
    Integer deleteTodo(Integer id);
    int[] deleteTodos(List<Map<String,Object>> params);
    Integer editTodos(Integer completed);
}
