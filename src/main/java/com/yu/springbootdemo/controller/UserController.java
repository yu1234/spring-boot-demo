package com.yu.springbootdemo.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yu.springbootdemo.domain.bean.User;
import com.yu.springbootdemo.domain.dao.UserMapper;
import com.yu.springbootdemo.service.IUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/users")     // 通过这里配置使下面的映射都在/users下
public class UserController {
    @Autowired
    private IUserService userService;

    @ApiOperation(value = "获取用户列表", notes = "")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> getUserList() {
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        List<User> users = null;
        try {
            EntityWrapper<User> entityWrapper = new EntityWrapper();
            users=  this.userService.selectList(entityWrapper);
        } catch (Exception e) {
            e.printStackTrace();
            users = new ArrayList<>();
        }
        List<User> r = new ArrayList<User>(users);
        return r;
    }

    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String postUser(@ModelAttribute User user) {
        // 处理"/users/"的POST请求，用来创建User
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        boolean i = false;
        try {
            user.setId("23");
          i=  user.insert();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (i) {
            return "success";
        }
        return "fail";
    }

    @ApiOperation(value = "根据id获取用户", notes = "根据id获取指定用户信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable String id) {
        // 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        User user = null;
        try {
            user = this.userService.selectById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @ApiOperation(value = "更新用户详细信息", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putUser(@PathVariable String id, @ModelAttribute User user) {
        // 处理"/users/{id}"的PUT请求，用来更新User信息
        User u = null;
        try {
            u = userService.selectById(id);
            if (u != null) {
                user.setId(id);
            }
            boolean i = this.userService.updateAllColumnById(user);
            if (i) {
                throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
        return "success";
    }

    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable String id) {
        // 处理"/users/{id}"的DELETE请求，用来删除User
        try {
            boolean i = this.userService.deleteById(id);
            if (i) {
                throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
        return "success";
    }
}
