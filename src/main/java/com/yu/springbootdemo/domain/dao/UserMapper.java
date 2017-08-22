package com.yu.springbootdemo.domain.dao;

import com.yu.springbootdemo.domain.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
public interface UserMapper {
    @Transactional(readOnly = true)
    @Select("SELECT * FROM USER")
    List<User> findAll() throws Exception;

    @Transactional(readOnly = true)
    @Select("SELECT * FROM USER WHERE NAME = #{name}")
    List<User> findByName(@Param("name") String name) throws Exception;

    @Transactional(readOnly = true)
    @Select("SELECT * FROM USER WHERE ID = #{id}")
    User findById(@Param("id") String id) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    @SelectKey(statement = "SELECT UUID()", keyProperty = "id", resultType = String.class, before = true)
    @Insert({"insert into user(id, name, password, age, deleteFlag) values(#{id}, #{name}, #{password}, #{age}, #{deleteFlag})"})
    int insert(User user) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    @Insert({"update user set name = #{name}, password = #{password}, age = #{age}, deleteFlag = #{deleteFlag} where id = #{id}"})
    int update(User user) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    @Insert({" delete from user where id = #{id}"})
    int deleteById(@Param("id") String id) throws Exception;
}
