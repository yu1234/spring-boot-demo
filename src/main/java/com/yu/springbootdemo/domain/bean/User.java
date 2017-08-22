package com.yu.springbootdemo.domain.bean;

import org.apache.ibatis.type.Alias;

/**
 * Created by igreentree on 2017/8/18 0018.
 */
@Alias("User")
public class User {
    private String id;
    private String name;
    private String password;
    private int age;
    private boolean deleteFlag;

    public User() {
    }

    public User(String id, String name, String password, int age, boolean deleteFlag) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
        this.deleteFlag = deleteFlag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
