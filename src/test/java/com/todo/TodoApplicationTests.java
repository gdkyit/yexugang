package com.todo;

import com.todo.dao.TodoDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TodoApplicationTests {
    @Autowired
    private TodoDao todoDao;

    @Test
    public void contextLoads() {
    }

}
