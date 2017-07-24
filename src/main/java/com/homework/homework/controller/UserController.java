package com.homework.homework.controller;

import com.homework.homework.utils.ResponseMessage;
import com.homework.homework.entiry.User;
import com.homework.homework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017  七月  21  0021.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /*
    * 查询特定用户
    * */
    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        List<Map<String, Object>> list = userService.getUser();
        ResponseMessage responseMessage = new ResponseMessage("200", "", list);
        return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
    }

    /*
    * 删
    * */
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        userService.delete(id);
        ResponseMessage responseMessage = new ResponseMessage("200", "", "删除成功");
        return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
    }

    /*
    * 修改
    * */
    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody User user) {
        userService.update(id, user);
        ResponseMessage responseMessage = new ResponseMessage("200", "", "修改成功");
        return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
    }

    /*
    * 增加
    * */
    @PostMapping("/users")
    public ResponseEntity<?> insertUser(@RequestBody User user) {
        userService.insert(user);
        ResponseMessage responseMessage = new ResponseMessage("200", "", "增加成功");
        return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
    }

}
