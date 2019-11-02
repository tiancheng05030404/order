package com.ruoyi.system.mapper;

import org.apache.ibatis.annotations.Param;

public interface BossMapper {
    Double getEnaringToday(@Param("shopId") Integer shopId, @Param("startTime") String startTime,@Param("endTime") String endTime);

    Double getEnaringMonth(@Param("shopId") Integer shopId, @Param("startTime") String startTime,@Param("endTime") String endTime);

    Double getEnaringWeek(@Param("shopId") Integer shopId, @Param("startTime") String startTime,@Param("endTime") String endTime);
}
