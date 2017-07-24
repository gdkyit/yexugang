package com.homework.homework.controller;

import com.homework.homework.utils.ResponseMessage;
import com.homework.homework.entiry.Role;
import com.homework.homework.service.RoleService;
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
public class RoleController {
    @Autowired
    private RoleService roleService;

    /*
   * 查询特定用户
   * */
    @GetMapping("/roles")
    public ResponseEntity<?> getUsers() {
        List<Map<String, Object>> list = roleService.getRole();
        ResponseMessage responseMessage = new ResponseMessage("200", "", list);
        return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
    }

    /*
    * 删
    * */
    @DeleteMapping("/roles/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        roleService.delete(id);
        ResponseMessage responseMessage = new ResponseMessage("200", "", "删除成功");
        return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
    }

    /*
    * 修改
    * */
    @PutMapping("/roles/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody Role role) {
        roleService.update(id, role);
        ResponseMessage responseMessage = new ResponseMessage("200", "", "修改成功");
        return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
    }

    /*
    * 增加
    * */
    @PostMapping("/roles")
    public ResponseEntity<?> insertUser(@RequestBody Role role) {
        roleService.insert(role);
        ResponseMessage responseMessage = new ResponseMessage("200", "", "增加成功");
        return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
    }
}
