package com.ruoyi.web.controller;


import javax.servlet.http.HttpServletRequest;


import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.base.Result;
import com.ruoyi.common.base.StringToObject;
import com.ruoyi.common.base.dishType;
import com.ruoyi.system.domain.Cart;
import com.ruoyi.system.mapper.CartMapper;
import com.ruoyi.system.service.ICartService;
import jdk.nashorn.internal.objects.annotations.Constructor;
import net.sf.json.JSONArray;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 处理购物车相关请求的控制器类
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    private ICartService cartService;

    @Autowired
    private CartMapper cartMapper;


    @PostMapping("/add")
    public Result addToCart(HttpServletRequest request) {
        JSONObject result = StringToObject.StringToJSon(request);
        Integer shopId = Integer.parseInt(result.getString("shopId"));
        Integer dishId = Integer.parseInt(result.getString("dishId"));
        String dishName = result.getString("dishName");
        String dishImage = result.getString("dishImage");
        Double dishPrice = Double.parseDouble(result.getString("dishPrice"));
        Integer number = Integer.parseInt(result.getString("number"));
        Integer tableId = Integer.parseInt(result.getString("tableId"));
        if (cartService.checkUniqueness(shopId, dishId, tableId) > 0) {
            Cart cart = new Cart();
            cart.setId(cartMapper.returnId(shopId, dishId, tableId).getId());
            cart.setShopId(shopId);
            cart.setTableId(tableId);
            cart.setNumber(number);
            cart.setTotalPrice(number * dishPrice);
            System.out.println("cart::::" + cart);
            cartService.updateNumber(cart);
            net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
            jsonObject.put("totalPrice", cartMapper.getTotalPrice(shopId, tableId)==null?0:cartMapper.getTotalPrice(shopId, tableId));
            return new Result(200, jsonObject, "菜品已添加,数量已修改");
        }
        Cart cart = new Cart();
        cart.setShopId(shopId);
        cart.setDishId(dishId);
        cart.setDishName(dishName);
        cart.setDishImage(dishImage);
        cart.setDishPrice(dishPrice);
        cart.setNumber(number);
        cart.setTableId(tableId);
        cart.setTotalPrice(number * dishPrice);
        // 调用业务层对象执行加入购物车功能
        if (cartService.addToCart(cart) > 0) {
            net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
            jsonObject.put("totalPrice", cartMapper.getTotalPrice(shopId, tableId)==null?0:cartMapper.getTotalPrice(shopId, tableId));
            return new Result(200, jsonObject, "购物车增加成功");
        } else {
            return new Result(203, "购物车增加失败");
        }
    }

    @GetMapping("/reduceNumber")
    public Result reduceNumber(HttpServletRequest request) {
        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        Integer dishId = Integer.parseInt(request.getParameter("dishId"));
        Integer tableId = Integer.parseInt(request.getParameter("tableId"));
        Integer id=cartMapper.returnId(shopId, dishId, tableId).getId();
        Integer number=cartMapper.returnId(shopId, dishId, tableId).getNumber()-1;
        if (number<=0){
            Cart cart = new Cart();
            cart.setTableId(tableId);
            cart.setShopId(shopId);
            cart.setId(id);
            cartService.deleteByCid(cart);
            net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
            jsonObject.put("totalPrice", cartMapper.getTotalPrice(shopId, tableId)==null?0:cartMapper.getTotalPrice(shopId, tableId));
            return new Result(200, jsonObject,"菜品数量小于或等于零,已删除");
        }
        Double price=cartMapper.returnId(shopId, dishId, tableId).getDishPrice();
        Cart cart = new Cart();
        cart.setId(id);
        cart.setShopId(shopId);
        cart.setTableId(tableId);
        cart.setNumber(number);
        cart.setTotalPrice(number * price);
        cartService.updateNumber(cart);
        net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
        jsonObject.put("totalPrice", cartMapper.getTotalPrice(shopId, tableId)==null?0:cartMapper.getTotalPrice(shopId, tableId));
        return new Result(200, jsonObject, "数量已修改");
    }


    @GetMapping("/select")
    public Result getByUid(HttpServletRequest request) {
        Integer tableId = Integer.parseInt(request.getParameter("tableId"));
        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        // 执行查询
        List<Cart> cartList = cartService.getByTableId(tableId, shopId);
        if (cartList.isEmpty()) {
            return new Result(202, "该用户尚未添加购物数据");
        }
        // 响应成功与查询结果
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (Cart cart : cartList) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", cart.getId());
            map.put("shopId", cart.getShopId());
            map.put("dishId",cart.getDishId());
            map.put("dishName", cart.getDishName());
            map.put("dishImage", cart.getDishImage());
            map.put("dishPrice", cart.getDishPrice());
            map.put("number", cart.getNumber());
            map.put("totalPrice", cart.getTotalPrice());
            list.add(map);
        }
        JSONArray jsonarr = JSONArray.fromObject(list);
        if (jsonarr.isEmpty()) {
            return new Result(211, "该用户没有数据");
        } else {
            return new Result(200, jsonarr, "成功");
        }
    }

    /**
     * 返回已有的购物车的数量和价格
     * @param request
     * @return
     */
    @GetMapping("/selectNumberAndPrice")
    public Result selectNumberAndPrice(HttpServletRequest request) {
        Integer tableId = Integer.parseInt(request.getParameter("tableId"));
        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        // 执行查询
        Map<String,Object> cartList = cartService.selectNumberAndPrice(tableId, shopId);
        // 响应成功与查询结果
        net.sf.json.JSONObject jsonObject=new net.sf.json.JSONObject();
        jsonObject.put("result",cartList);
        if (jsonObject.isEmpty()) {
            return new Result(211, "该用户没有数据");
        } else {
            return new Result(200, jsonObject, "成功");
        }
    }



    @PostMapping("/updateNumber")
    public Result addNum(HttpServletRequest request) {

        JSONObject result = StringToObject.StringToJSon(request);
        Integer tableId = Integer.parseInt(result.getString("tableId"));
        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        Integer number = Integer.parseInt(request.getParameter("number"));
        Integer id = Integer.parseInt(request.getParameter("cartId"));
        Double dishPrice = Double.parseDouble(result.getString("dishPrice"));
        Cart cart = new Cart();
        cart.setTableId(tableId);
        cart.setShopId(shopId);
        cart.setId(id);
        cart.setNumber(number);
        cart.setTotalPrice(number * dishPrice);
        Integer rows = cartService.updateNumber(cart);
        if (rows > 0) {
            net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
            jsonObject.put("totalPrice", cartMapper.getTotalPrice(shopId, tableId));
            return new Result(200, jsonObject, "修改菜品数量成功");
        } else {
            return new Result(205, "修改菜品数量失败");
        }
    }


    @PostMapping("deleteByCid")
    public Result deleteByCid(HttpServletRequest request) {
        JSONObject result = StringToObject.StringToJSon(request);
        Integer tableId = Integer.parseInt(result.getString("tableId"));
        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        Integer id = Integer.parseInt(request.getParameter("cartId"));
        Cart cart = new Cart();
        cart.setTableId(tableId);
        cart.setShopId(shopId);
        cart.setId(id);
        int data = cartService.deleteByCid(cart);
        // 返回成功与查询结果
        if (data != 0) {
            return new Result(200, "成功");
        } else {
            return new Result(210, "失败");
        }
    }


}






