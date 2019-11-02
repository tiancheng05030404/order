package com.ruoyi.web.controller;

import java.util.Date;

import com.ruoyi.common.base.Result;
import com.ruoyi.system.domain.Dish;
import com.ruoyi.system.mapper.OrderMapper;
import com.ruoyi.system.mapper.TableMapper;
import com.ruoyi.system.service.BossService;
import com.ruoyi.system.service.IOrderService;
import com.ruoyi.system.service.TableService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * 老板报表模块
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RestController
@RequestMapping("/boss")
public class BossController {

    @Autowired
    BossService bossService;

    @Autowired
    TableService tableService;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    IOrderService orderService;

    @Autowired
    TableMapper tableMapper;


    /**
     * @param request
     * @return 今日收益
     */
    @GetMapping("/selectAll")
    public Result selectAll(HttpServletRequest request) {
        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        String startTime = request.getParameter("startTime")==null?null:request.getParameter("startTime");
        String endTime = request.getParameter("endTime")==null?null:request.getParameter("endTime");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("today", bossService.getEarningToday(shopId, startTime, endTime));
        jsonObject.put("hotList", orderMapper.dishHot(shopId,startTime,endTime));
        jsonObject.put("maxInCome", orderMapper.maxInCome(shopId,startTime,endTime));
        jsonObject.put("month", bossService.getEarningMonth(shopId, startTime, endTime));
        jsonObject.put("week", bossService.getEarningWeek(shopId, startTime, endTime));
        if (jsonObject.isEmpty()) {
            return new Result(203, "暂无收益");
        } else {
            return new Result(200, jsonObject, "收益统计信息已发送");
        }
    }


    /**
     * @param request
     * @return 今日收益
     */
    @GetMapping("/income/today")
    public Result incomeToday(HttpServletRequest request) {
        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        String startTime = request.getParameter("startTime")==null?null:request.getParameter("startTime");
        String endTime = request.getParameter("endTime")==null?null:request.getParameter("endTime");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("today", bossService.getEarningToday(shopId, startTime, endTime));
        jsonObject.put("hotList", orderMapper.dishHot(shopId,startTime,endTime));
        jsonObject.put("totalTurnover", orderMapper.maxInCome(shopId,startTime,endTime));
        if (jsonObject.isEmpty()) {
            return new Result(203, "暂无收益");
        } else {
            return new Result(200, jsonObject, "当天收益");
        }
    }


    /**
     * 当月收益
     *
     * @param request
     * @return
     */
    @GetMapping("/income/month")
    public Result incomeMonth(HttpServletRequest request) {
        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        String startTime = request.getParameter("startTime")==null?null:request.getParameter("startTime");
        String endTime = request.getParameter("endTime")==null?null:request.getParameter("endTime");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("today", bossService.getEarningToday(shopId, startTime, endTime));
        jsonObject.put("hotList", orderMapper.dishHotMonth(shopId,startTime,endTime));
        jsonObject.put("totalTurnover", orderMapper.maxInComeMonth(shopId,startTime,endTime));
        jsonObject.put("month", bossService.getEarningMonth(shopId, startTime, endTime));
        if (jsonObject.isEmpty()) {
            return new Result(203, "暂无收益");
        } else {
            return new Result(200, jsonObject, "当月收益");
        }

    }

    /**
     * 当周收益
     *
     * @param request
     * @return
     */
    @GetMapping("/income/week")
    public Result incomeyear(HttpServletRequest request) {
        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        String startTime = request.getParameter("startTime")==null?null:request.getParameter("startTime");
        String endTime = request.getParameter("endTime")==null?null:request.getParameter("endTime");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("today", bossService.getEarningToday(shopId, startTime, endTime));
        jsonObject.put("hotList", orderMapper.dishHotWeek(shopId,startTime,endTime));
        jsonObject.put("totalTurnover", orderMapper.maxInComeWeek(shopId,startTime,endTime));
        jsonObject.put("week", bossService.getEarningWeek(shopId, startTime, endTime));
        if (jsonObject.isEmpty()) {
            return new Result(203, "暂无收益");
        } else {
            return new Result(200, jsonObject, "本周收益");
        }

    }

    /**
     * @param
     * @return 餐桌号与餐桌情况
     */
    @GetMapping("/table/status")
    public Result tableStatus(HttpServletRequest request) {
        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        Integer floorId = Integer.parseInt(request.getParameter("floorId") == null ? "-1" : request.getParameter("floorId"));
        Integer speId = Integer.parseInt(request.getParameter("speId") == null ? "-1" : request.getParameter("speId"));
        Integer status = Integer.parseInt(request.getParameter("status") == null ? "-1" : request.getParameter("status"));
        Integer area = Integer.parseInt(request.getParameter("area") == null ? "-1" : request.getParameter("area"));

        net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
        jsonObject.put("specification", tableService.selectSpecification(shopId));
        jsonObject.put("floor", tableService.selectFloor(shopId));
        jsonObject.put("status", tableMapper.selectStatus());
        jsonObject.put("tableList", tableService.selectTable(shopId, floorId, speId, status, area));
        return new Result(200, jsonObject, "餐桌列表已发送");
    }

    /**
     * shopId 餐桌id
     * orderId 订单id
     *
     * @param
     * @return 订单（菜品名称，餐品单价，餐品数量）
     */
    @GetMapping("/table/current")
    public Result selectbalbeid(HttpServletRequest request) {
        Integer shopId = Integer.parseInt(request.getParameter(" shopId"));
        Integer tableId = Integer.parseInt(request.getParameter(" tableId"));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderDetails", orderService.selectByTableId(shopId, tableId));
        return new Result(200, jsonObject, "订单详情");
    }


    /**
     * @param
     * @return 菜单卖出排行
     * （菜品名称、菜单单价、卖出数量、总收益）
     */
    @GetMapping("/dishHot")
    public Result dishHot(HttpServletRequest request) {
        //得到商户id
        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        String startTime = request.getParameter("startTime")==null?null:request.getParameter("startTime");
        String endTime = request.getParameter("endTime")==null?null:request.getParameter("endTime");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("HotList", orderMapper.dishHot(shopId,startTime,endTime));
        if (jsonObject.equals("") || jsonObject == null) {
            return new Result(1, "网络错误");
        } else {
            return new Result(200, jsonObject, "菜品排行");
        }
    }

}
