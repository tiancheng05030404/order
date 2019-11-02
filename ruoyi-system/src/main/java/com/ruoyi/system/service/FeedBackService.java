package com.ruoyi.system.service;

import com.ruoyi.system.domain.FeedBack;
import org.omg.CORBA.INTERNAL;

import java.util.List;

/**
 * 意见反馈的服务层
 */
public interface FeedBackService {

    Integer add(Integer shopId,String openId,String remark);

    List<FeedBack> select(Integer shopId,String openId);

    Integer  update(Integer shopId,String openId,String remark);

    Integer delete(Integer shopId,String openId);

}
