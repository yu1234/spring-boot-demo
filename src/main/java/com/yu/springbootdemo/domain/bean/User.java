package com.yu.springbootdemo.domain.bean;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.KeySequence;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * Created by igreentree on 2017/8/18 0018.
 */
@Alias("User")
public class User extends Model<User> {
    public final static String ATTR_ID="id";
    public final static String ATTR_NAME="NAME";
    public final static String ATTR_PASSWORD="password";
    public final static String ATTR_AGE="age";
    public final static String ATTR_DELETE_FLAG="deleteFlag";

    private String id;
    private String name;
    private String password;
    private int age;
    @TableField("deleteFlag")
    private boolean deleteFlag;

    public User() {
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
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
