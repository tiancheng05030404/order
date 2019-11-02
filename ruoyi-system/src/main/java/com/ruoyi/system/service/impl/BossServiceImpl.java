package com.ruoyi.system.service.impl;

import com.ruoyi.system.mapper.BossMapper;
import com.ruoyi.system.service.BossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class BossServiceImpl implements BossService {

    @Autowired
    BossMapper bossMapper;


    @Override
    public Double getEarningToday(Integer shopId,String startTime,String endTime) {
        return bossMapper.getEnaringToday(shopId,startTime,endTime);
    }

    @Override
    public Double getEarningMonth(Integer shopId,String startTime,String endTime) {
        return bossMapper.getEnaringMonth(shopId,startTime,endTime);
    }

    @Override
    public Double getEarningWeek(Integer shopId,String startTime,String endTime) {
        return bossMapper.getEnaringWeek(shopId,startTime,endTime);
    }
}
