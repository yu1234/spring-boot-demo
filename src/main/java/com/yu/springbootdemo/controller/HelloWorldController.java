package com.yu.springbootdemo.controller;

import com.alibaba.fastjson.JSON;
import com.yu.springbootdemo.domain.dao.UserMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created by igreentree on 2017/8/21 0021.
 */
@RestController
public class HelloWorldController {
    @Autowired
    private UserMapper userMapper;

    @ApiOperation(value = "spring boot test 首页", notes = "跳转到首页")
    @RequestMapping(value ={ "/","/index"}, method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }
    @ApiOperation(value = "用户登录", notes = "用户登录页")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    @ApiOperation(value = "spring boot 入门方法", notes = "spring boot 入门方法")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() throws Exception {
        return JSON.toJSONString("hello world");
    }
}
