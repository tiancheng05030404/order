package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    //查询用户列表
    List<User> findAll();

    //删除用户
    int deleteById(int userId);

    //添加用户
    int add(User user);

    //验证账户密码
    User selectUser(@Param("userName") String userName,@Param("password") String password);
}
