package com.ruoyi.web.controller;


import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;


import com.alibaba.fastjson.JSONObject;

import com.ruoyi.common.base.RandomNumber;
import com.ruoyi.common.base.Result;
import com.ruoyi.common.base.StringToObject;


import com.ruoyi.system.domain.Cart;
import com.ruoyi.system.domain.Order;
import com.ruoyi.system.domain.OrderDTO;
import com.ruoyi.system.mapper.OrderMapper;
import com.ruoyi.system.service.ICartService;
import com.ruoyi.system.service.IOrderService;
import com.ruoyi.system.service.TableService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RestController
@RequestMapping("order")
public class OrderController {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat mon = new SimpleDateFormat("yyyy-MM");


    @Autowired
    private IOrderService orderservice;

    @Autowired
    private ICartService cartService;

    @Autowired
    private TableService tableService;

    @Autowired
    OrderMapper orderMapper;


    //通过shopId查询所有订单
    @RequestMapping("/list")
    public ModelAndView list(@RequestParam("shopId") Integer shopId,
                             Map<String, Object> map){
        List<Order> orderList=orderservice.finAll(shopId);
        map.put("shopId",shopId);
        map.put("orderList",orderList);
        return new ModelAndView("order/list", map);
    }

    //通过OrderId和shopId查询订单详情
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               @RequestParam("shopId") Integer shopId,
                               Map<String, Object> map){
        OrderDTO orderDTO=new OrderDTO();
        List<Order> orderList=orderservice.selectByOrderId(orderId,shopId);
        Double orderAmount = 0.0;
        for (int i = 0; i < orderList.size(); i++) {
            Double price = orderList.get(i).getTotalPrice();
            orderAmount += price;
        }
        orderDTO.setOrderAmount(orderAmount);
        orderDTO.setOrderId(orderList.get(0).getOrderId());
        map.put("orderDTO",orderDTO);
        map.put("shopId",shopId);
        map.put("orderList" , orderList);
        return new ModelAndView("order/detail",map);
    }


    /**
     * 生成新的订单
     *
     * @param request
     * @return
     */
    @PostMapping("add")
    public Result addNew(HttpServletRequest request) {
        JSONObject result = StringToObject.StringToJSon(request);
        List<Order> list = (List<Order>) JSONArray.toList(JSONArray.fromObject(result.getString("dishArray")), Order.class);
        Integer shopId = Integer.parseInt(result.getString("shopId"));
        Integer tableId = Integer.parseInt(result.getString("tableId"));
        String openId = result.getString("openId") == null ? null : result.getString("openId");
        String remark = result.getString("remark");
        String orderId = RandomNumber.GetRandom();

        for (Order or : list) {
            Order order = new Order();
            order.setOrderId(orderId);
            order.setDishId(or.getDishId());
            order.setDishImage(or.getDishImage());
            order.setDishName(or.getDishName());
            order.setNumber(or.getNumber());
            order.setDishPrice(or.getDishPrice());
            order.setTotalPrice(or.getNumber() * or.getDishPrice());
            order.setShopId(shopId);
            order.setOpenId(openId);
            order.setTableId(tableId);
            order.setRemark(remark);
            orderservice.addNew(order);
            Cart cart = new Cart();
            cart.setTableId(tableId);
            cart.setShopId(shopId);
            cart.setId(or.getCid());
            int row = cartService.deleteByCid(cart);
            tableService.updateTableStatus(shopId, tableId, 2, -1);
        }
        orderservice.updateOrderTime(shopId, orderId, new Date());
        //返回订单数据
        net.sf.json.JSONObject json = new net.sf.json.JSONObject();
        json.put("orderId", orderId);
        return new Result(200, json, "成功");
    }

    /**
     * 通过orderId查看订单信息
     *
     * @param request
     * @return
     */
    @GetMapping("select")
    public Result selectByOrderId(HttpServletRequest request) {
        List<Order> order = orderservice.selectByOrderId(request.getParameter("orderId"), Integer.parseInt(request.getParameter("shopId")));
        if (order.isEmpty()){
            return new Result(205,"该订单查无信息");
        }
        Double allPrice = 0.00;
        for (int i = 0; i < order.size(); i++) {
            Double price = order.get(i).getTotalPrice();
            allPrice += price;
        }
        List<Map<String, Object>> list = new ArrayList<>();
        for (Order or : order) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("orderId", or.getOrderId());
            map.put("dishId", or.getDishId());
            map.put("dishName", or.getDishName());
            map.put("dishImages", or.getDishImage());
            map.put("dishPrice", or.getDishPrice());
            map.put("number", or.getNumber());
            map.put("totalPrice", or.getTotalPrice());
            map.put("status", or.getDishStatus());
            list.add(map);
        }
        JSONArray jsonarr = JSONArray.fromObject(list);
        net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
        jsonObject.put("jsonArray", list);
        jsonObject.put("totalPrice", allPrice);
        return new Result(200, jsonObject, "成功");
    }


    /**
     * 查看菜品的状态
     *
     * @param request
     * @return
     */
    @GetMapping("selectStatus")
    public Result selectStatus(HttpServletRequest request) {
        System.out.println(request.getParameter("orderId") + Integer.parseInt(request.getParameter("shopId")));
        List<Order> orderList = orderservice.selectStatus(request.getParameter("orderId"), Integer.parseInt(request.getParameter("shopId")));
        List<Map<String, Object>> list = new ArrayList<>();
        for (Order or : orderList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("dishName", or.getDishName());
            map.put("dishImage", or.getDishImage());
            map.put("status", or.getDishStatus());
            list.add(map);
        }
        JSONArray jsonarr = JSONArray.fromObject(list);
        return new Result(200, jsonarr, "状态已发送");
    }

    /**
     * 通过orderId一键修改订单状态
     * 0: 已出单 1:正在做 2:已上菜 3:已退菜 4: 已付款
     *
     * @param request
     * @return
     */
    @GetMapping("changeStatusByOrderId")
    public Result changeStatusByOrderId(HttpServletRequest request) {
        Integer status = Integer.parseInt(request.getParameter("status"));
        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        String orderId = request.getParameter("orderId");
        int rows = orderservice.changeStatusByOrderId(status, shopId, orderId);
        if (rows > 0) {
            return new Result(200, null, "订单状态修改成功");
        } else {
            return new Result(203, null, "订单状态修改失败");
        }

    }

    /**
     * 单个修改菜品状态
     * 0: 已出单 1:正在做 2:已上菜 3:已退菜
     *
     * @param request
     * @return
     */
    @GetMapping("changeStatusByOrderIdAndDishId")
    public Result changeStatusByOrderIdAndDishId(HttpServletRequest request) {
        Integer status = Integer.parseInt(request.getParameter("status"));
        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        Integer dishId = Integer.parseInt(request.getParameter("dishId"));
        String orderId = request.getParameter("orderId");
        int rows = orderservice.changeStatusByOrderIdAndDishId(status, shopId, dishId, orderId);
        if(status==3) {
            orderMapper.change0(shopId, dishId, orderId);
        }
        if (rows > 0) {
            return new Result(200, null, "订单状态修改成功");
        } else {
            return new Result(203, null, "订单状态修改失败");
        }
    }

    /**
     * 最后一次消费记录
     *
     * @param request
     * @return
     */
    @GetMapping("selectLatest")
    public Result selectLatest(HttpServletRequest request) {

        List<Order> order = orderservice.selectDesc(request.getParameter("openId"), Integer.parseInt(request.getParameter("shopId")));
        if (order.isEmpty()) {
            return new Result(202, "查询失败，未查询到该用户的上次消费记录");
        }
        Double allPrice = 0.00;
        for (int i = 0; i < order.size(); i++) {
            Double price = order.get(i).getTotalPrice();
            allPrice += price;
        }
        List<Map<String, Object>> list = new ArrayList<>();
        for (Order or : order) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("orderId", or.getOrderId());
            map.put("dishName", or.getDishName());
            map.put("dishImages", or.getDishImage());
            map.put("dishPrice", or.getDishPrice());
            map.put("number", or.getNumber());
            map.put("totalPrice", or.getTotalPrice());
            list.add(map);
        }
        JSONArray jsonarr = JSONArray.fromObject(list);
        net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
        jsonObject.put("jsonArray", list);
        jsonObject.put("totalPrice", allPrice);
        return new Result(200, jsonObject, "成功");
    }

    /**
     * 删除某一订单
     *
     * @param request
     * @return
     */
    @GetMapping("delete")
    public Result deleteByTableId(HttpServletRequest request) {

        if (orderservice.deleteByTableId(Integer.parseInt(request.getParameter("tableId")), Integer.parseInt(request.getParameter("shopId"))) != 0) {
            return new Result(200, null, "成功");
        } else {
            return new Result(213, null, "失败");
        }
    }

    /**
     * 查询某人在某商家消费过的全部订单
     *
     * @param request
     * @return
     */
    @GetMapping("/all")
    public Result selectAll(HttpServletRequest request) {

        List<Order> data = orderservice.getList(request.getParameter("openId"), Integer.parseInt(request.getParameter("shopId")));
        // 返回“成功”和以上查询到的结果
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (Order or : data) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("orderId", or.getOrderId());
            map.put("dishName", or.getDishName());
            map.put("dishImages", or.getDishImage());
            map.put("dishPrice", or.getDishPrice());
            map.put("number", or.getNumber());
            map.put("totalPrice", or.getTotalPrice());
            list.add(map);
        }
        JSONArray jsonarr = JSONArray.fromObject(list);
        return new Result(200, jsonarr, "订单发送");
    }

    /**
     * 修改订单的支付状态和支付时间
     *
     * @param request
     * @return
     */
    @GetMapping("updateStatusAndPayTime")
    public Result updateStatusAndPayTime(HttpServletRequest request) {
        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        String orderId = request.getParameter("orderId");
        int rows = orderservice.updateStatusAndPayTime(shopId, orderId, new Date());
        if (rows > 0) {
            return new Result(200, "订单已支付修改成功");
        } else {
            return new Result(202, "订单修改失败");
        }
    }


}
