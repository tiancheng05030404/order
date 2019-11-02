package com.ruoyi.system.service;

import com.ruoyi.system.domain.User;

import java.util.List;

public interface UserService {

    //查询用户列表
    List<User> findAll();

    //删除用户
    int deleteById(int userId);

    //添加用户
    int add(User user);

    //登录
    User selectUser(String userName,String password);
}
