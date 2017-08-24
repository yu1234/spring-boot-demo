package com.yu.springbootdemo.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yu.springbootdemo.domain.bean.User;
import com.yu.springbootdemo.domain.dao.UserMapper;
import com.yu.springbootdemo.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
