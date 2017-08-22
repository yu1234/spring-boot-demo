package com.yu.springbootdemo.controller;

import com.alibaba.fastjson.JSON;

import com.yu.springbootdemo.domain.bean.User;
import com.yu.springbootdemo.domain.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by igreentree on 2017/8/21 0021.
 */
@RestController
public class HelloWorldController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/hello")
    public String index() {
        List<User> users = userMapper.findByName("ji");
        return JSON.toJSONString(users);
    }
    @RequestMapping("/dbInsert")
    public String dbInsert(){
        int i = userMapper.insert("ji", "123456");
        return i+"";
    }
}
