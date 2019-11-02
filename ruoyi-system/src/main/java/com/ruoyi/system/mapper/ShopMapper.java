package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopMapper {

    //添加商家
    Integer addShop(Shop shop);

    //修改店铺信息
    Integer updateShop(Shop shop);

    //查询商家列表
    List<Shop>  selectAll();

    //查看商家详情
    Shop shopDetails(Integer id);

    //删除商家
    Integer deleteShop(Integer id);

    List<Shop> Allselect(Integer shoppId);
}
