package com.ruoyi.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.base.Result;
import com.ruoyi.common.base.StringToObject;
import com.ruoyi.common.base.dishType;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.OrderMapper;
import com.ruoyi.system.mapper.TableMapper;
import com.ruoyi.system.service.ICartService;
import com.ruoyi.system.service.IOrderService;
import com.ruoyi.system.service.TableService;
import io.swagger.annotations.Api;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.awt.geom.Area;
import java.util.*;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RestController
@RequestMapping(value = "/table")
public class TableController {

    @Autowired
    TableService tableService;

    @Autowired
    TableMapper tableMapper;

    @Autowired
    IOrderService orderService;

    @Autowired
    ICartService cartService;

    @Autowired
    OrderMapper orderMapper;

    /**
     * 餐桌模块各种信息的展示
     *
     * @param request
     * @return
     */
    @GetMapping("/index/tableList")
    public Result selectAll(HttpServletRequest request) {
        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        Integer floorId = Integer.parseInt(request.getParameter("floorId") == "" ? "-1" : request.getParameter("floorId"));
        Integer speId = Integer.parseInt(request.getParameter("speId") == "" ? "-1" : request.getParameter("speId"));
        Integer status = Integer.parseInt(request.getParameter("status") == "" ? "-1" : request.getParameter("status"));
        Integer area = Integer.parseInt(request.getParameter("area") == "" ? "-1" : request.getParameter("area"));

        net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
        jsonObject.put("tableList", tableService.selectTable(shopId, floorId, speId, status, area));
        return new Result(200, jsonObject, "餐桌列表已发送");
    }

    /**
     * 餐桌规格信息的展示
     *
     * @param request
     * @return
     */
    @GetMapping("/index/specification")
    public Result selectspecification(HttpServletRequest request) {
        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
        jsonObject.put("specification", tableService.selectSpecification(shopId));
        return new Result(200, jsonObject, "餐桌列表已发送");
    }

    /**
     * 餐桌楼层信息的展示
     *
     * @param request
     * @return
     */
    @GetMapping("/index/floor")
    public Result selectFloor(HttpServletRequest request) {
        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
        jsonObject.put("floor", tableService.selectFloor(shopId));
        return new Result(200, jsonObject, "餐桌列表已发送");
    }

    /**
     * 餐桌区域信息的展示
     *
     * @param request
     * @return
     */
    @GetMapping("/index/area")
    public Result selectArea1(HttpServletRequest request) {
        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        Integer floorId = Integer.parseInt(request.getParameter("floorId") == "" ? "-1" : request.getParameter("floorId"));
        net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
        jsonObject.put("area", tableService.selectArea(shopId, floorId == -1 ? 1 : floorId));
        return new Result(200, jsonObject, "餐桌列表已发送");
    }

    /**
     * 显示正在用餐的餐桌的订单
     */
    @GetMapping("selectOrder")
    public Result selectOrder(HttpServletRequest request) {
        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        Integer tableId = Integer.parseInt(request.getParameter("tableId"));
        List<Order> order = orderService.selectByTableId(shopId, tableId);
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
            map.put("status", or.getStatus());
            list.add(map);
        }
        net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
        jsonObject.put("jsonArray", list);
        jsonObject.put("totalPrice", allPrice);
        return new Result(200, jsonObject, "餐桌订单详情");
    }


    /**
     * 添加商家的餐桌信息
     *
     * @param //shopId   商家Id
     * @param //tableNum 商家桌子数
     * @return
     */
    @GetMapping("/add")
    public Result addTable(HttpServletRequest request) {

        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        Integer floorId = Integer.parseInt(request.getParameter("floorId") == null ? "1" : request.getParameter("floorId"));
        Integer areaId = Integer.parseInt(request.getParameter("areaId") == null ? "1" : request.getParameter("floorId"));
        Integer speId = Integer.parseInt(request.getParameter("speId") == null ? "1" : request.getParameter("floorId"));
        Integer tableNum = Integer.parseInt(request.getParameter("tableNum"));

        int j = 0;
        for (int i = 0; i < tableNum; i++) {
            if (tableMapper.getMaxId(shopId) != null) {
                String name = tableMapper.getMaxId(shopId) + 1 + "";
                tableService.addTable(shopId, floorId, areaId, speId, name);
                j++;
            } else {
                if (i <= 9) {
                    int a = i + 1;
                    String name = "00" + a;
                    tableService.addTable(shopId, floorId, areaId, speId, name);
                    j++;
                } else if (i >= 10 && 100 > i) {
                    int a = i + 1;
                    String name = "0" + a;
                    tableService.addTable(shopId, floorId, areaId, speId, name);
                    j++;
                } else {
                    int a = i + 1;
                    String name = a + "";
                    tableService.addTable(shopId, floorId, areaId, speId, name);
                    j++;
                }
            }
        }
        if (j == tableNum) {
            return new Result(200, "商家添加餐桌信息成功,已添加" + j + "张");
        } else {
            return new Result(201, "商家添加餐桌信息失败");
        }
    }


    /**
     * 修改餐桌名字
     *
     * @param request
     * @return
     */
    @GetMapping("/updateTableName")
    public Result updateTable(HttpServletRequest request) {
        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        Integer tableId = Integer.parseInt(request.getParameter("tableId"));
        String tableName = request.getParameter("tableName");
        int rows = tableService.updateTableName(shopId, tableId, tableName);
        if (rows > 0) {
            return new Result(200, "餐桌名字信息更新成功");
        } else {
            return new Result(203, "餐桌名字信息更新失败");
        }
    }

    /**
     * 修改餐桌状态
     * 0:空闲  1: 已开桌  2:用餐  3:待清理
     *
     * @param request
     * @return
     */
    @GetMapping("/updateTableStatus")
    public Result updateTableStatus(HttpServletRequest request) {

        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        Integer tableId = Integer.parseInt(request.getParameter("tableId"));
        Integer status = Integer.parseInt(request.getParameter("status"));
        Integer population = Integer.parseInt(request.getParameter("population")) == 0 ? -1 : Integer.parseInt(request.getParameter("population"));
        if (status == 3) {
            orderService.deleteByTableId(tableId, shopId);
        }
        int rows = tableService.updateTableStatus(shopId, tableId, status, population);
        if (rows > 0) {
            return new Result(200, "餐桌状态更新成功");
        } else {
            return new Result(203, "餐桌状态更新失败");
        }
    }

    /**
     * 清台
     *
     * @param request
     * @return
     */
    @GetMapping("/cleanTable")
    public Result cleanTable(HttpServletRequest request) {

        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        Integer tableId = Integer.parseInt(request.getParameter("tableId"));
        Integer population = Integer.parseInt(request.getParameter("population") == null ? "-1" : request.getParameter("population"));
        orderService.deleteByTableId(tableId, shopId);
        int rows = tableService.updateTableStatus(shopId, tableId, 0, population);
        if (rows > 0) {
            return new Result(200, "餐桌清台成功");
        } else {
            return new Result(203, "餐桌清台失败");
        }
    }

    /**
     * 修改餐桌区域
     * 1:大厅  2: 包厢
     *
     * @param request
     * @return
     */
    @GetMapping("/updateTableArea")
    public Result updateTableArea(HttpServletRequest request) {

        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        Integer tableId = Integer.parseInt(request.getParameter("tableId"));
        Integer area = Integer.parseInt(request.getParameter("area"));
        int rows = tableService.updateTableArea(shopId, tableId, area);
        if (rows > 0) {
            return new Result(200, "餐桌状态更新成功");
        } else {
            return new Result(203, "餐桌状态更新失败");
        }
    }

    /**
     * 撤桌
     *
     * @param request
     * @return
     */
    @GetMapping("/deleteTable")
    public Result deleteTable(HttpServletRequest request) {
        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        Integer tableId = Integer.parseInt(request.getParameter("tableId"));
        int rows = tableService.deleteTable(shopId, tableId);
        if (rows > 0) {
            return new Result(200, "餐桌删除成功");
        } else {
            return new Result(203, "餐桌删除失败");
        }
    }

    /**
     * 通过tableId获取orderId
     *
     * @param request
     * @return
     */
    @GetMapping("/getOrderIdByTableId")
    public Result getOrderIdByTableId(HttpServletRequest request) {
        Integer tableId = Integer.parseInt(request.getParameter("tableId"));
        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        String orderId = orderService.getOrderIdByTableId(tableId, shopId);
        net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
        jsonObject.put("orderId", orderId);
        if (jsonObject.isEmpty()) {
            return new Result(204, "查询失败，未开单");
        }
        return new Result(200, jsonObject, "订单号已发送");
    }


    /**
     * 加菜
     *
     * @param request
     * @return
     */
    @PostMapping("/increaseDish")
    public Result addDish(HttpServletRequest request) {

        JSONObject result = StringToObject.StringToJSon(request);

        List<Order> list = (List<Order>) JSONArray.toList(JSONArray.fromObject(result.getString("dishArray")), Order.class);
        Integer shopId = Integer.parseInt(result.getString("shopId"));
        Integer tableId = Integer.parseInt(result.getString("tableId"));
        String orderId = result.getString("orderId");
        int rows = 0;
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
            order.setTableId(tableId);

            if (orderMapper.checkExist(shopId, or.getDishId(), orderId) > 0) {
                Integer number = orderMapper.getDishNumber(shopId, or.getDishId(), orderId)+or.getNumber();
                orderMapper.updateNumber(number,number* or.getDishPrice(),shopId, or.getDishId(), orderId);
                Cart cart = new Cart();
                cart.setTableId(tableId);
                cart.setShopId(shopId);
                cart.setId(or.getCid());
                cartService.deleteByCid(cart);
                rows++;
                continue;
            }

            orderService.addDish(order);
            Cart cart = new Cart();
            cart.setTableId(tableId);
            cart.setShopId(shopId);
            cart.setId(or.getCid());
            cartService.deleteByCid(cart);
            rows++;
        }

        if (rows == list.size()) {
            return new Result(200, "加菜成功");
        } else {
            return new Result(201, "加菜失败");
        }
    }


    //==================================楼层================================================================================


    /**
     * 添加楼层信息
     *
     * @param request
     * @return
     */
    @GetMapping("addFloor")
    public Result addFloor(HttpServletRequest request) {
        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        String floorName = request.getParameter("floorName");
        int rows = tableService.addFloor(shopId, floorName);
        if (rows > 0) {
            return new Result(200, "商家添加楼层信息成功");
        } else {
            return new Result(201, "商家添加楼层信息失败");
        }
    }

    /**
     * 更新楼层名字
     *
     * @param request
     * @return
     */
    @GetMapping("updateFloorName")
    public Result updateFloorName(HttpServletRequest request) {
        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        String floorName = request.getParameter("floorName");
        Integer floorId = Integer.parseInt(request.getParameter("floorId"));

        int rows = tableService.updateFloorName(shopId, floorId, floorName);
        if (rows > 0) {
            return new Result(200, "餐桌名字信息更新成功");
        } else {
            return new Result(203, "餐桌名字信息更新失败");
        }
    }


    /**
     * 减楼层
     *
     * @param request
     * @return
     */
    @GetMapping("/deleteFloor")
    public Result deleteFloor(HttpServletRequest request) {
        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        Integer floorId = Integer.parseInt(request.getParameter("floorId"));
        int rows = tableService.deleteFloor(shopId, floorId);
        tableMapper.deleteByFloor(floorId, shopId);
        if (rows > 0) {
            return new Result(200, "餐桌删除成功");
        } else {
            return new Result(203, "餐桌删除失败");
        }
    }


    //==================================规格================================================================================

    @GetMapping("/specification/add")
    public Result addSpecification(HttpServletRequest request) {
        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        String specification = request.getParameter("specification");
        int rows = tableService.addSpecification(shopId, specification);
        if (rows > 0) {
            return new Result(200, "规格信息添加成功");
        } else {
            return new Result(202, "规格信息添加失败");
        }
    }

    @GetMapping("/specification/update")
    public Result updateSpecification(HttpServletRequest request) {
        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        Integer speId = Integer.parseInt(request.getParameter("speId"));
        String specification = request.getParameter("specification");
        int rows = tableService.updateSpecification(shopId, speId, specification);
        if (rows > 0) {
            return new Result(200, "规格信息修改成功");
        } else {
            return new Result(202, "规格信息修改失败");
        }
    }

    @GetMapping("/specification/delete")
    public Result deleteSpecification(HttpServletRequest request) {
        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        Integer speId = Integer.parseInt(request.getParameter("speId"));
        int rows = tableService.deleteSpecification(shopId, speId);
        tableMapper.deleteBySpe(speId, shopId);
        if (rows > 0) {
            return new Result(200, "规格信息删除成功");
        } else {
            return new Result(202, "规格信息删除失败");
        }
    }

    @GetMapping("/specification/selcet")
    public Result selectSpecification(HttpServletRequest request) {
        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        List<TableSpecification> rows = tableService.selectSpecification(shopId);
        List<Map<String, Object>> list = new ArrayList<>();
        for (TableSpecification tableSpecification : rows) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("speId", tableSpecification.getId());
            map.put("specification", tableSpecification.getSpecification());
            list.add(map);
        }
        JSONArray jsonarr = JSONArray.fromObject(list);
        return new Result(200, jsonarr, "成功");
    }

    //==========================================区域====================================================================

    @GetMapping("/area/add")
    public Result addArea(HttpServletRequest request) {
        Integer floorId = Integer.parseInt(request.getParameter("floorId"));
        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        String area = request.getParameter("area");
        Integer rows = tableService.addArea(shopId, floorId, area);
        if (rows > 0) {
            return new Result(200, "商家餐桌区域添加成功");
        } else {
            return new Result(202, "商家餐桌区域添加失败");
        }
    }


    @GetMapping("/area/update")
    public Result updateArea(HttpServletRequest request) {
        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        Integer areaId = Integer.parseInt(request.getParameter("areaId"));
        String area = request.getParameter("area");
        Integer rows = tableService.updateArea(shopId, areaId, area);
        if (rows > 0) {
            return new Result(200, "商家餐桌区域修改成功");
        } else {
            return new Result(202, "商家餐桌区域修改失败");
        }
    }

    @GetMapping("/area/select")
    public Result selectArea(HttpServletRequest request) {
        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        Integer floorId = Integer.parseInt(request.getParameter("floorId") == "" ? "-1" : request.getParameter("floorId"));
        net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
        jsonObject.put("area", tableService.selectArea(shopId, floorId == -1 ? 1 : floorId));
        return new Result(200, jsonObject, "餐桌列表已发送");
    }

    @GetMapping("/area/delete")
    public Result deleteArea(HttpServletRequest request) {
        Integer shopId = Integer.parseInt(request.getParameter("shopId"));
        Integer areaId = Integer.parseInt(request.getParameter("areaId") == "" ? "-1" : request.getParameter("floorId"));
        Integer rows = tableService.deleteArea(shopId, areaId);
        tableMapper.deleteByArea(areaId, shopId);
        if (rows > 0) {
            return new Result(200, "商家餐桌区域修改成功");
        } else {
            return new Result(202, "商家餐桌区域修改失败");
        }
    }

}
