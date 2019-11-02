package com.ruoyi.system.service;

import com.ruoyi.system.domain.Shop;

import java.util.List;

public interface ShopService {
    //增加商铺
    Integer addShop(Shop shop);

    //修改店铺信息
    Integer updateShop(Shop shop);

    //查询商铺列表
    List<Shop> selectAll();

    //查看商家详情
    Shop shopDetails(Integer id);

    //删除店铺
    Integer deleteShop(Integer id);

    //查询店铺信息
    List<Shop> Allselect(Integer shopid);
}
