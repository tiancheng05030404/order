package com.ruoyi.system.service;

/**
 * 老板报表的服务层
 */
public interface BossService {

    Double getEarningToday(Integer shopId,String startTime,String endTime);

    Double getEarningMonth(Integer shopId,String startTime,String endTime);

    Double getEarningWeek(Integer shopId,String startTime,String endTime);

}
