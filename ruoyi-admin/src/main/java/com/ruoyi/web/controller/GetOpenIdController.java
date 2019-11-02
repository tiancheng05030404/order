package com.ruoyi.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.base.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/authorize")
public class GetOpenIdController{


    @RequestMapping(value = "/getOpenId", method = RequestMethod.POST)
    public Map decodeUserInfo(String code) {

        Map map = new HashMap();
        //登录凭证不能为空
        if (code == null || code.length() == 0) {
            map.put("status", 0);
            map.put("msg", "code 不能为空");
            return map;
        }
        //小程序唯一标识   (在微信小程序管理后台获取)
        String wxspAppid = "wxd0ccefffed1623c5";
        //小程序的 app secret (在微信小程序管理后台获取)
        String wxspSecret = "6d777f9bceb827b31934faefa1e69f2f";


        //////////////// 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid ////////////////
        //请求参数
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type=authorization_code";
        //发送请求
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
        //解析相应内容（转换成json对象）
        JSONObject json = JSONObject.parseObject(sr);
        //获取会话密钥（session_key）
        String session_key = json.get("session_key").toString();
        //用户的唯一标识（openid）
        String openid = (String) json.get("openid");

        map.put("session_key", session_key);
        map.put("openid", openid);
        return map;
    }
}
