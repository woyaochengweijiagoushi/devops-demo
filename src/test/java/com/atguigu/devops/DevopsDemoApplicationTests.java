package com.atguigu.devops;

import com.atguigu.devops.controller.HelloController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DevopsDemoApplicationTests {


    @Autowired
    HelloController helloController;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(helloController);
    }

}
