package com.yu.springbootdemo;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yu.springbootdemo.domain.bean.User;
import com.yu.springbootdemo.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisTests {
    @Autowired
    private IUserService userService;

    /**
     * 查询全部测试
     */
    @Test
    public void selectAllTest(){
        List<User> users=this.userService.selectList(new EntityWrapper<User>());
        System.out.println(JSON.toJSONString(users));
    }
}

