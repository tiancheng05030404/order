package com.ruoyi.system.domain;

import lombok.Data;

@Data
public class User {

    //userid
    private Integer id;

    //用户名
    private String userName;

    //密码
    private String password;

    //商铺ID
    private Integer sid;

    //用户类型
    private Integer userType;
}
