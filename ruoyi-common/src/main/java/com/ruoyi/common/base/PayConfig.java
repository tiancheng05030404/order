package com.ruoyi.common.base;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 第三方支付的配置文件
 * @author
 *
 */
public class PayConfig {

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    String date=df.format(new Date()).replaceAll("-","").replaceAll(" ","").replaceAll(":","");

    //小程序appid
    public static final String appid = "wxd0ccefffed1623c5";

//    //商户号
//    public static final String mercId = "800121000036172";
//    //机构号
//    public static final String orgNo = "65896";
//    //设备号
//    public static final String trmNo = "XB304558";


    public static final String mercId = "800121000038152";
    //机构号
    public static final String orgNo = "42285";
    //设备号
    public static final String trmNo = "XB345898";

//    //设备端交易时间
    public static final String txnTime = "";
    //版本号
    public static final String version = "V1.0.0";
    //实付金额
    public static final String amout = "";
    //total_amout
    public static final String total_amout = "";

    //测试地址
    public static final String dev_url = "http://sandbox.starpos.com.cn/adpweb/ehpspos3/pubSigPay.json";

    //测试地址
    public static final String pro_url = "http://gateway.starpos.com.cn/adpweb/ehpspos3/pubSigPay.json";

    public static void main(String[] args) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date=df.format(new Date()).replaceAll("-","").replaceAll(" ","").replaceAll(":","");
        System.out.println(date);// new Date()为获取当前系统时间
    }
}
