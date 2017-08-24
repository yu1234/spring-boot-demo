package com.yu.springbootdemo.domain.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yu.springbootdemo.domain.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<User> {

}
