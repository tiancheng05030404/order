package com.ruoyi.web.controller;

import com.ruoyi.common.base.AliOssUtil;
import com.ruoyi.common.base.Result;
import com.ruoyi.system.domain.Menu;
import com.ruoyi.system.domain.Shop;
import com.ruoyi.system.service.ShopService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@RestController
@RequestMapping("shop")
public class ShopController {

    @Value("${oss.endpoint}")
    private String endpoint;

    @Value("${oss.accessId}")
    private String accessId;

    @Value("${oss.accessKey}")
    private String accessKey;

    @Value("${oss.bucket1}")
    private String bucket;


    @Autowired
    ShopService shopService;

    /**
     * 添加商家信息
     *
     * @param request
     * @param shopName
     * @param address
     * @param phone
     * @param wifi
     * @param longitude
     * @param latitude
     * @return
     * @throws IOException
     */
    @PostMapping("add")
    public Result addShop(HttpServletRequest request,
                          @RequestParam(value = "shopName", required = false, defaultValue = "") String shopName,
                          @RequestParam(value = "address", required = false, defaultValue = "") String address,
                          @RequestParam(value = "phone", required = false, defaultValue = "") String phone,
                          @RequestParam(value = "wifi", required = false, defaultValue = "") String wifi,
                          @RequestParam(value = "longitude", required = false, defaultValue = "") String longitude,
                          @RequestParam(value = "latitude", required = false, defaultValue = "") String latitude,
                          @RequestParam(value = "orgNo", required = false, defaultValue = "") String orgNo,
                          @RequestParam(value = "mercId", required = false, defaultValue = "") String mercId,
                          @RequestParam(value = "trmNo", required = false, defaultValue = "") String trmNo
    ) throws IOException {
        MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
        MultipartFile file = req.getFile("file");
        InputStream inputStream = file.getInputStream();
        String[] suffix = file.getOriginalFilename().split("\\.");
        int suffixIndex = suffix.length - 1;
        String ossPath = AliOssUtil.uploadFile(inputStream, bucket, endpoint, accessId, accessKey, shopName + new Date().getTime() + "." + suffix[suffixIndex]);
        System.out.println("````````````````" + ossPath);

        Shop shop = new Shop();
        shop.setName(shopName);
        shop.setAddress(address);
        shop.setAvatar(ossPath);
        shop.setPhone(phone);
        shop.setWifi(wifi);
        shop.setLongitude(longitude);
        shop.setLatitude(latitude);
        shop.setOrgNo(orgNo);
        shop.setMercId(mercId);
        shop.setTrmNo(trmNo);
        if (shopService.addShop(shop) > 0) {
            return new Result(200, "店铺添加成功");
        } else {
            return new Result(202, "添加失败");
        }
    }

    /**
     * 更新商家信息
     *
     * @param request
     * @param shopId
     * @param shopName
     * @param address
     * @param phone
     * @param wifi
     * @param longitude
     * @param latitude
     * @return
     * @throws IOException
     */
    @PostMapping("update")
    public Result updateShop(HttpServletRequest request,
                             @RequestParam(value = "shopId", required = false, defaultValue = "") Integer shopId,
                             @RequestParam(value = "shopName", required = false, defaultValue = "") String shopName,
                             @RequestParam(value = "address", required = false, defaultValue = "") String address,
                             @RequestParam(value = "phone", required = false, defaultValue = "") String phone,
                             @RequestParam(value = "wifi", required = false, defaultValue = "") String wifi,
                             @RequestParam(value = "longitude", required = false, defaultValue = "") String longitude,
                             @RequestParam(value = "latitude", required = false, defaultValue = "") String latitude
    ) throws IOException {
        MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
        MultipartFile file = req.getFile("file");
        InputStream inputStream = file.getInputStream();
        String[] suffix = file.getOriginalFilename().split("\\.");
        int suffixIndex = suffix.length - 1;
        String ossPath = AliOssUtil.uploadFile(inputStream, bucket, endpoint, accessId, accessKey, shopName + new Date().getTime() + "." + suffix[suffixIndex]);
        System.out.println("````````````````" + ossPath);

        Shop shop = new Shop();
        shop.setId(shopId);
        shop.setName(shopName);
        shop.setAddress(address);
        shop.setAvatar(ossPath);
        shop.setPhone(phone);
        shop.setWifi(wifi);
        shop.setLongitude(longitude);
        shop.setLatitude(latitude);

        if (shopService.updateShop(shop) > 0) {
            return new Result(200, "店铺信息修改成功");
        } else {
            return new Result(202, "店铺信息修改失败");
        }
    }

    /**
     * 查询商家列表
     *
     * @return
     */
    @GetMapping("selectAll")
    public Result selectAll() {
        List<Shop> shopList = shopService.selectAll();
        if (shopList.isEmpty()) {
            return new Result(202, "尚未接入商家");
        }
        List<Map<String, Object>> list = new ArrayList<>();
        for (Shop shop : shopList) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", shop.getId());
            map.put("name", shop.getName());
            map.put("avatar", shop.getAvatar());
            map.put("address", shop.getAddress());
            map.put("wifi", shop.getWifi());
            map.put("phone", shop.getPhone());
            map.put("longitude", shop.getLongitude());
            map.put("latitude", shop.getLatitude());
            list.add(map);
        }
        JSONArray jsonArray = JSONArray.fromObject(list);
        return new Result(200, jsonArray, "商家列表已发送");
    }

    /**
     * 查询商家列表
     *
     * @return
     */
    @GetMapping("shopDetails")
    public Result shopDetails(HttpServletRequest request) {
        Integer shopid = Integer.parseInt(request.getParameter("shopId"));
        Shop shop = shopService.shopDetails(shopid);
        if (StringUtils.isEmpty(shop)) {
            return new Result(202, "未查询到该商家信息");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("id", shop.getId()==null?"":shop.getId());
        map.put("name", shop.getName()==null?"":shop.getName());
        map.put("avatar", shop.getAvatar()==null?"":shop.getAvatar());
        map.put("address", shop.getAddress()==null?"":shop.getAddress());
        map.put("wifi", shop.getWifi()==null?"":shop.getWifi());
        map.put("phone", shop.getPhone()==null?"":shop.getPhone());
        map.put("longitude", shop.getLongitude()==null?"":shop.getLongitude());
        map.put("latitude", shop.getLatitude()==null?"":shop.getLatitude());
        map.put("details", shop.getDetails().split(";")==null?"":shop.getDetails().split(";"));
        map.put("banner", shop.getBanner().split(";")==null?"":shop.getBanner().split(";"));
        return new Result(200, JSONObject.fromObject(map), "商家列表已发送");
    }

    /**
     * 删除商家
     *
     * @param request
     * @return
     */
    @GetMapping("delete")
    public Result deleteShop(HttpServletRequest request) {
        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        if (shopService.deleteShop(shopId) > 0) {
            return new Result(200, "删除成功");
        } else {
            return new Result(203, "删除失败");
        }
    }

}
