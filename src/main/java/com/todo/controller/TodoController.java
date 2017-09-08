package com.todo.controller;

import com.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017  八月  17  0017.
 */
@Controller
public class TodoController {
    @Autowired
    private TodoService todoService;

    @RequestMapping("/")
    public String index() {
        return "hello";
    }

    //获取全部
    @GetMapping(value = "/todos/all", produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<Map<String, Object>> getAll() {
        return todoService.getAll();
    }

    //获取completed为1的数据
    @GetMapping(value = "/todos/active", produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<Map<String, Object>> getActive() {
        return todoService.getActive();
    }

    //获取completed为2的数据
    @GetMapping(value = "/todos/completed", produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<Map<String, Object>> getCompleted() {
        return todoService.getCompleted();
    }

    @PostMapping(value = "/todos")
    @ResponseBody
    public ResponseEntity<?> addTodo(@RequestBody Map<String, Object> param) {
        return new ResponseEntity<Object>(todoService.addTodo((String) param.get("title")), HttpStatus.OK);
    }

    @PutMapping(value = "/todos")
    @ResponseBody
    public ResponseEntity<?> editTodo(@RequestBody Map<String, Object> param) {
        return new ResponseEntity<Object>(todoService.editTodo((Integer) param.get("id"), (Integer) param.get("completed")), HttpStatus.OK);
    }

    @DeleteMapping("/todos")
    @ResponseBody
    public ResponseEntity<?> deleteTodo(@RequestBody Map<String, Object> param) {
        return new ResponseEntity<Object>(todoService.deleteTodo((Integer) param.get("id")), HttpStatus.OK);
    }

    //清空所有
    @DeleteMapping("/todos/all")
    @ResponseBody
    public ResponseEntity<?> deleteTodos(@RequestBody List<Map<String, Object>> params) {
        return new ResponseEntity<Object>(todoService.deleteTodos(params), HttpStatus.OK);
    }

    //修改所有
    @PutMapping("/todos/all")
    @ResponseBody
    public ResponseEntity<?> editTodos(@RequestBody Map<String, Object> param) {
        return new ResponseEntity<Object>(todoService.editTodos((Integer) param.get("completed")), HttpStatus.OK);
    }

}
