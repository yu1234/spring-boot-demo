package com.yu.springbootdemo.domain.dao;

import com.yu.springbootdemo.domain.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;


import java.util.List;

public interface UserMapper {
    @Select("SELECT * FROM USER WHERE NAME = #{name}")
    List<User> findByName(@Param("name") String name);
    @SelectKey(statement ="SELECT UUID()",keyProperty = "id",resultType = String.class,before = true)
    @Insert({"INSERT INTO USER(ID,NAME, PASSWORD) VALUES(#{id},#{name}, #{password})"})
    int insert(@Param("name") String name, @Param("password") String password);
}
