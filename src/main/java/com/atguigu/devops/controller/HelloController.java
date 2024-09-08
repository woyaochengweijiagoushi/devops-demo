package com.atguigu.devops.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lfy
 * @Description
 * @create 2022-12-28 10:58
 */
@RestController
public class HelloController {

    @GetMapping({"/","/hello"})
    public String hello(){
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return "现在是！！！："+format;
    }
}
