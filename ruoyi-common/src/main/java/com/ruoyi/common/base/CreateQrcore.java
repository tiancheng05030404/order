package com.ruoyi.common.base;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

public class CreateQrcore {

    /*
     * 获取 token
     * 普通的 get 可获 token
     */
    public  static String getToken() {
        try {
            Map<String, String> map = new LinkedHashMap<>();
            map.put("grant_type", "client_credential");
            map.put("appid","wxd0ccefffed1623c5");
            map.put("secret", "6d777f9bceb827b31934faefa1e69f2f");

            String rt = UrlUtil.sendPost("https://api.weixin.qq.com/cgi-bin/token", map);
            System.out.println("what is:"+rt);
            JSONObject json = JSONObject.fromObject(rt);

            if (json.getString("access_token") != null || json.getString("access_token") != "") {
                return json.getString("access_token");
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * 获取 二维码图片
     *
     */
    public static String getminiqrQr( String accessToken,HttpServletRequest request) {
        String p="F://code"; //二维码生产的地址  本地F盘code文件夹
        System.out.println(p);
        String codeUrl=p+"/twoCode.png";
        String twoCodeUrl="twoCode.png";
        try
        {
            URL url = new URL("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+accessToken);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");// 提交模式
            // conn.setConnectTimeout(10000);//连接超时 单位毫秒
            // conn.setReadTimeout(2000);//读取超时 单位毫秒
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            // 发送请求参数
            JSONObject paramJson = new JSONObject();
            paramJson.put("scene","shopId=1");//这就是你二维码里携带的参数 String型  名称不可变
            paramJson.put("path", "pages/index/index"); //这是设置扫描二维码后跳转的页面
            paramJson.put("width", 430);
            paramJson.put("is_hyaline", true);
            paramJson.put("auto_color", true);

            System.out.println("httpURLConnection"+httpURLConnection);
            System.out.println("paramJson.toString()"+paramJson.toString());
            printWriter.write(paramJson.toString());
            // flush输出流的缓冲
            printWriter.flush();
            //开始获取数据
            BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());

            File file = new File(codeUrl);
            if(!file.exists()){
                //先得到文件的上级目录，并创建上级目录，在创建文件
                file.getParentFile().mkdir();
                try {
                    //创建文件
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            FileOutputStream os = new FileOutputStream(new File(codeUrl));
            int len;
            byte[] arr = new byte[1024];
            while ((len = bis.read(arr)) != -1)
            {
                os.write(arr, 0, len);
                os.flush();
            }
            os.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return twoCodeUrl;
    }
}