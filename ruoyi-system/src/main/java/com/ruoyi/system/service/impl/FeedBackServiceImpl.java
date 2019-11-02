package com.ruoyi.system.service.impl;


import com.ruoyi.system.domain.FeedBack;
import com.ruoyi.system.mapper.FeedBackMapper;
import com.ruoyi.system.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class FeedBackServiceImpl implements FeedBackService {

    @Autowired
    FeedBackMapper feedBackMapper;

    @Override
    public Integer add(Integer shopId, String openId, String remark) {
        return feedBackMapper.add(shopId, openId, remark);
    }

    @Override
    public List<FeedBack> select(Integer shopId, String openId) {
        return feedBackMapper.select(shopId,openId);
    }

    @Override
    public Integer update(Integer shopId, String openId,String remark) {
        return feedBackMapper.update(shopId,openId,remark);
    }

    @Override
    public Integer delete(Integer shopId, String openId) {
        return feedBackMapper.delete(shopId,openId);
    }
}
