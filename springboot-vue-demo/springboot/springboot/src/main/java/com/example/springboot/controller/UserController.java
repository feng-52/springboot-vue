package com.example.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.User;
import com.example.springboot.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

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
    //  定义get接口
    @GetMapping
    /**
     * @pageNum 当前页 默认为1
     * @pageSize 分页大小 默认为10
     * @search 查询关键字 默认为空字符串
     */
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search)
    {
        //Page是MybatiesPlus提供的类
        //分页对象   Wrappers 条件构造器, 通过搜索框绑定的search值，在用户列表中拆模糊查询
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        //当search为空时查询全部
        if (StrUtil.isNotBlank(search)){
            wrapper.like(User::getNickName,search);
        }
        Page<User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(userPage);
    }
    //    一般put进行更新
    @PutMapping
    public Result<?> update(@RequestBody User user)
    {
        userMapper.updateById(user);
        return Result.success();
    }
    //占位符方式传入参数id  必须用@PathVariable接收
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id)
    {
        userMapper.deleteById(id);
        return Result.success();
    }



}
