package com.ruoyi.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.base.*;
import com.ruoyi.common.utils.wxPay.*;
import com.ruoyi.system.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/weixin")
public class PaymentController {

    @Autowired
    private IOrderService orderservice;

    /**
     * @param request
     * @Description: 发起微信支付
     */
    @PostMapping("/payment")
    public Map<String, Object> wxPay(HttpServletRequest request) {

        String str = StringToObject.parseString(request);
        JSONObject jb = JSONObject.parseObject(str);

        String orderId = jb.getString("orderId");
//        String  body = jb.getString("name");
        String money = jb.getString("money");
        String openid = jb.getString("openId");
        try {
            //生成的随机字符串
            String nonce_str = StringUtils.getRandomStringByLength(32);
            //商品名称
//            String body = "测试商品名称";
            //获取客户端的ip地址
            String spbill_create_ip = IpUtils.getIpAddr(request);
            //组装参数，用户生成统一下单接口的签名
            Map<String, String> packageParams = new HashMap<String, String>();
            packageParams.put("appid", WxPayConfig.appid);
            packageParams.put("mch_id", WxPayConfig.mch_id);
            packageParams.put("nonce_str", nonce_str);
            packageParams.put("body", "发起支付");
            packageParams.put("out_trade_no", orderId);//商户订单号
            packageParams.put("total_fee", money);//支付金额，这边需要转成字符串类型，否则后面的签名会失败
            packageParams.put("spbill_create_ip", spbill_create_ip);
            packageParams.put("notify_url", WxPayConfig.notify_url);//支付成功后的回调地址
            packageParams.put("trade_type", WxPayConfig.TRADETYPE);//支付方式
            packageParams.put("openid", openid);
            String prestr = PayUtil.createLinkString(packageParams); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
            //MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
            String mysign = PayUtil.sign(prestr, WxPayConfig.key, "utf-8").toUpperCase();
            //拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去
            String xml = "<xml>" + "<appid>" + WxPayConfig.appid + "</appid>"
                    + "<body><![CDATA[" + "发起支付" + "]]></body>"
                    + "<mch_id>" + WxPayConfig.mch_id + "</mch_id>"
                    + "<nonce_str>" + nonce_str + "</nonce_str>"
                    + "<notify_url>" + WxPayConfig.notify_url + "</notify_url>"
                    + "<openid>" + openid + "</openid>"
                    + "<out_trade_no>" + orderId + "</out_trade_no>"
                    + "<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>"
                    + "<total_fee>" + "1" + "</total_fee>"
                    + "<trade_type>" + WxPayConfig.TRADETYPE + "</trade_type>"
                    + "<sign>" + mysign + "</sign>"
                    + "</xml>";
            System.out.println("调试模式_统一下单接口 请求XML数据：" + xml);
            //调用统一下单接口，并接受返回的结果
            String result = PayUtil.httpRequest(WxPayConfig.pay_url, "POST", xml);
            System.out.println("调试模式_统一下单接口 返回XML数据：" + result);
            // 将解析结果存储在HashMap中
            Map map = PayUtil.doXMLParse(result);
            String return_code = (String) map.get("return_code");//返回状态码
            Map<String, Object> response = new HashMap<String, Object>();//返回给小程序端需要的参数
            if (return_code.equals("SUCCESS")) {
                String prepay_id = (String) map.get("prepay_id");//返回的预付单信息
                response.put("nonceStr", nonce_str);
                response.put("package", "prepay_id=" + prepay_id);
                Long timeStamp = System.currentTimeMillis() / 1000;
                response.put("timeStamp", timeStamp + "");//这边要将返回的时间戳转化成字符串，不然小程序端调用wx.requestPayment方法会报签名错误
                //拼接签名需要的参数
                String stringSignTemp = "appId=" + WxPayConfig.appid + "&nonceStr=" + nonce_str + "&package=prepay_id=" + prepay_id + "&signType=MD5&timeStamp=" + timeStamp;
                //再次签名，这个签名用于小程序端调用wx.requesetPayment方法
                String paySign = PayUtil.sign(stringSignTemp, WxPayConfig.key, "utf-8").toUpperCase();

                response.put("paySign", paySign);
            }
            response.put("appid", WxPayConfig.appid);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @return
     * @throws Exception
     * @Description:微信支付回调
     */
    @RequestMapping(value = "/wxNotify")
    @ResponseBody
    public void wxNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        //sb为微信返回的xml
        String notityXml = sb.toString();
        String resXml = "";
        System.out.println("接收到的报文：" + notityXml);
        Map map = PayUtil.doXMLParse(notityXml);
        String returnCode = (String) map.get("return_code");
        if ("SUCCESS".equals(returnCode)) {
            //验证签名是否正确
            Map<String, String> validParams = PayUtil.paraFilter(map);  //回调验签时需要去除sign和空值参数
            String validStr = PayUtil.createLinkString(validParams);//把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
            String sign = PayUtil.sign(validStr, WxPayConfig.key, "utf-8").toUpperCase();//拼装生成服务器端验证的签名
            //根据微信官网的介绍，此处不仅对回调的参数进行验签，还需要对返回的金额与系统订单的金额进行比对等
            if (sign.equals(map.get("sign"))) {
                /**此处添加自己的业务逻辑代码start  out_trade_no**/


                /**此处添加自己的业务逻辑代码end**/
                //通知微信服务器已经支付成功
                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
            }
        } else {
            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                    + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        }
        System.out.println(resXml);
        System.out.println("微信支付回调数据结束");
        BufferedOutputStream out = new BufferedOutputStream(
                response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();
    }


//----------------------------------------------第三方支付-------------------------------------------------------------------


    @GetMapping("/pay")
    public Result payMnet(HttpServletRequest request) {
        String money=request.getParameter("money");
        String openId=request.getParameter("openId");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date()).replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
        Map<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("appid", PayConfig.appid);
        jsonObject.put("orgNo", PayConfig.orgNo);
        jsonObject.put("mercId", PayConfig.mercId);
        jsonObject.put("trmNo", PayConfig.trmNo);
        jsonObject.put("txnTime", date);
        jsonObject.put("version", PayConfig.version);
        jsonObject.put("amount", money);
        jsonObject.put("total_amount", money);
        jsonObject.put("openid", openId);
        String json = JSON.toJSONString(jsonObject);
        System.out.println("请求报文"+json);
        try {
            String str = HttpUtil.post(PayConfig.pro_url, json);

            System.out.println("返回结果"+str);

            JSONObject result = JSONObject.parseObject(str);
            if (result.getString("returnCode").equals("000000")) {
                net.sf.json.JSONObject jb = new net.sf.json.JSONObject();
                jb.put("nonceStr", result.getString("apiNoncestr"));
                jb.put("package", result.getString("apiPackage"));
                jb.put("timeStamp", result.getString("apiTimestamp"));
                jb.put("paySign", result.getString("apiPaysign"));
                jb.put("signType", result.getString("apiSigntype"));
                return new Result(200, jb, "支付发起结果");
            } else {
                return new Result(211, "支付发起失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}