package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.FeedBack;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FeedBackMapper {
    Integer add(@Param("shopId") Integer shopId, @Param("openId") String openId ,@Param("remark")String remark);

    List<FeedBack> select(@Param("shopId") Integer shopId, @Param("openId") String openId);

    Integer update(@Param("shopId") Integer shopId, @Param("openId") String openId,@Param("remark")String remark);

    Integer delete(@Param("shopId") Integer shopId, @Param("openId") String openId);
}
