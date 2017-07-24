package com.homework.homework.controller;

import com.homework.homework.entiry.Menu;
import com.homework.homework.utils.ResponseMessage;
import com.homework.homework.service.MenuService;
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
public class MenuController {
    @Autowired
    private MenuService menuService;

    /*
   * 查询特定菜单功能
   * */
    @GetMapping("/menus")
    public ResponseEntity<?> getMenus() {
        List<Map<String, Object>> list = menuService.getMenu();
        ResponseMessage responseMessage = new ResponseMessage("200", "", list);
        return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
    }

    /*
    * 删
    * */
    @DeleteMapping("/menus/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        menuService.delete(id);
        ResponseMessage responseMessage = new ResponseMessage("200", "", "删除成功");
        return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
    }

    /*
    * 修改
    * */
    @PutMapping("/menus/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody Menu menu) {
        menuService.update(id, menu);
        ResponseMessage responseMessage = new ResponseMessage("200", "", "修改成功");
        return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
    }

    /*
    * 增加
    * */
    @PostMapping("/menus")
    public ResponseEntity<?> insertUser(@RequestBody Menu menu) {
        menuService.insert(menu);
        ResponseMessage responseMessage = new ResponseMessage("200", "", "增加成功");
        return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
    }
}
