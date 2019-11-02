package com.ruoyi.system.domain;

import lombok.Data;

@Data
public class Shop {
    Integer id;
    String name;
    String avatar;
    String address;
    String wifi;
    String phone;

    //经度
    String longitude;
    //纬度
    String latitude;

    //店铺详情展示图
    String details;

    //轮播图
    String banner;

    //机构号
    String orgNo;

    //商家号
    String mercId;

    //设备号
    String  trmNo;
}
