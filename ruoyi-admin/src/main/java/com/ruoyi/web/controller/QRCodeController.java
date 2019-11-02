package com.ruoyi.web.controller;

import com.ruoyi.common.base.CreateQrcore;
import net.sf.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;

@RestController
public class QRCodeController {


    /**
     * 接收二维码
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/twoCode",method= RequestMethod.GET)
    @ResponseBody
    public Object twoCode( HttpServletRequest request) throws IOException {

        JSONObject data=new JSONObject();
        String accessToken = CreateQrcore.getToken();
        System.out.println("accessToken;"+accessToken);
        String twoCodeUrl = CreateQrcore.getminiqrQr(accessToken,request);
        data.put("twoCodeUrl", twoCodeUrl);
        return data;
    }
}
