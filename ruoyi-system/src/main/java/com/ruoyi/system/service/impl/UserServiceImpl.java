package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.User;
import com.ruoyi.system.mapper.UserMapper;
import com.ruoyi.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public int deleteById(int userId) {
        return userMapper.deleteById(userId);
    }

    @Override
    public int add(User user) {
        return userMapper.add(user);
    }

    @Override
    public User selectUser(String userName,String password){
     return userMapper.selectUser(userName,password);
    }
}
