package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.User;
import com.example.springboot.mapper.UserMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

//定义是返回json的controller
@RestController
//定义统一的路由
@RequestMapping("/user")
public class UserController
{
    //引入UserMaooer
    @Resource
    UserMapper userMapper;

    //  定义post接口
    @PostMapping
    //  新增 RequestBody注解把前台传来的数据转换成java对象实体
    public Result<?> save(@RequestBody User user)
    {
        //使用userMapper的insert方法新增user对象
        userMapper.insert(user);
        return Result.success();
    }
}
