package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.Shop;
import com.ruoyi.system.mapper.ShopMapper;
import com.ruoyi.system.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopMapper shopMapper;


    @Override
    public Integer addShop(Shop shop) {
        return shopMapper.addShop(shop);
    }

    @Override
    public Integer updateShop(Shop shop) {
        return shopMapper.updateShop(shop);
    }

    @Override
    public List<Shop> selectAll() {
        return shopMapper.selectAll();
    }

    @Override
    public Shop shopDetails(Integer id) {
        return shopMapper.shopDetails(id);
    }

    @Override
    public Integer deleteShop(Integer id) {
        return shopMapper.deleteShop(id);
    }
    @Override
    public List<Shop> Allselect(Integer shopId){
        return shopMapper.Allselect(shopId);
    }
}
