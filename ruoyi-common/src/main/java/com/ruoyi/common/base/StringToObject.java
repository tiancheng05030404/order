package com.ruoyi.common.base;


import com.alibaba.fastjson.JSONObject;


import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

import java.util.Map;

public  class StringToObject {

    public static JSONObject StringToJSon(HttpServletRequest request) {
        JSONObject jb = null;
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader();) {
            char[] buff = new char[1024];
            int len;
            while ((len = reader.read(buff)) != -1) {
                sb.append(buff, 0, len);
            }
            jb = JSONObject.parseObject(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jb;
    }


    /**
     * 解析json字符串
     * @param request
     * @return  josn字符串
     */
    public static String parseString(HttpServletRequest request) {
        String json=null;
        Enumeration enu=request.getParameterNames();
        while(enu.hasMoreElements()){
            String paraName=(String)enu.nextElement();
            json = paraName + ": " + request.getParameter(paraName);
        }
        return json;
    }

    /**
     * 解析json字符串
     * @param request
     * @return  josn字符串
     */
    public static String parseString(HttpServletRequest request,String parse,int number) {
        String json=null;
        Enumeration enu=request.getParameterNames();
        while(enu.hasMoreElements()){
            String paraName=(String)enu.nextElement();
            json = paraName + ": " + request.getParameter(paraName);
            System.out.println(json);
            String[]arr=json.split(parse+":");
            json=arr[1].substring(0,arr[1].length()-number);
            json=json.replaceAll("\"","");
            System.out.println(json);
        }
        return json;
    }

    /**
     * 解析json字符串成map
     * @param request
     * @return  map
     */
    public static Map<String,String> parseStringToMap(HttpServletRequest request,String parse,int number) {
        String str=StringToObject.parseString(request,parse,number);
        str=str.substring(1,str.length()-1);
        String[] arr=str.split(",");
        Map<String,String> map=new HashMap<>();
        for(String parameter:arr){
            String[]flag=parameter.split(":");
            if(flag.length>0){
                map.put(flag[0],flag.length==1?"":flag[1]);
            }
        }
        return map;
    }



}




