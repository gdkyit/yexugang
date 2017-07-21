package com.restful.controller;

import com.restful.entiry.ResponseMessage;
import com.restful.entiry.User;
import com.restful.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017  七月  20  0020.
 */
@Controller
public class RestController {
    @Autowired
    private RestService restService;


    @Autowired
    JdbcTemplate jdbcTemplate;

    /*
    * 查
    * */
    @GetMapping("/user")
    public ResponseEntity<?> getResult() throws Exception {
        List<Map<String, Object>> users = restService.get();
        ResponseMessage rm = new ResponseMessage("200", "", users);
        return new ResponseEntity<Object>(rm, HttpStatus.OK);
    }

    /*
    * 查某一个
    * */
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id) throws Exception {
        //User user = restService.getOne(id);
        Map<String, Object> map = jdbcTemplate.queryForMap("select * from xugang where id=?", id);
        ResponseMessage rm = new ResponseMessage("200", "", restService.getMap(id));
        return new ResponseEntity<Object>(rm, HttpStatus.OK);
    }

    /*
    * 增
    * */
    @PostMapping("/user")
    public ResponseEntity<?> postResult(@RequestBody User user) throws Exception {
        restService.insert(user);
        ResponseMessage rm = new ResponseMessage("200", "", "增加成功");
        return new ResponseEntity<Object>(rm, HttpStatus.OK);
    }

    /*
    * 批量增加
    * */
    @PostMapping("/batch/user")
    public ResponseEntity<?> postBatchResult(@RequestBody List<User> users) throws Exception {
        restService.insert(users);
        ResponseMessage rm = new ResponseMessage("200", "", "增加成功");
        return new ResponseEntity<Object>(rm, HttpStatus.OK);
    }

    /*
    * 改
    * */
    @PutMapping("/user/{id}")
    public ResponseEntity<?> putResult(@PathVariable Integer id, @RequestBody User user) throws Exception {
        restService.update(id, user);
        ResponseMessage rm = new ResponseMessage("200", "", "修改成功");
        return new ResponseEntity<Object>(rm, HttpStatus.OK);
    }


    /*
    * 删
    * */
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteResult(@PathVariable Integer id) throws Exception {
        restService.delete(id);
        ResponseMessage rm = new ResponseMessage("200", "", "删除成功");
        return new ResponseEntity<Object>(rm, HttpStatus.OK);
    }
}
