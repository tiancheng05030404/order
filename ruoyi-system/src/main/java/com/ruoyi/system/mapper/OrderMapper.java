package com.ruoyi.system.mapper;


import com.ruoyi.system.domain.Boss;
import com.ruoyi.system.domain.Dish;
import com.ruoyi.system.domain.Order;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.INTERNAL;

import java.util.Date;
import java.util.List;
import java.util.Map;


public interface OrderMapper {


    Integer addNew(Order order);

    //更改订单的下单时间
    Integer updateOrderTime(@Param("shopId") Integer shopId, @Param("orderId") String orderId , @Param("orderTime")Date orderTime);

    List<Order> selectByOrderId(@Param("orderId") String oderId, @Param("shopId") Integer shopId);

    List<Order> selectDesc(@Param("openId") String openId, @Param("shopId") Integer shopId);

    List<Order> getList(@Param("openId") String openId, @Param("shopId") Integer shopId);

    Integer deleteByTableId(@Param("tableId") Integer tableId, @Param("shopId") Integer shopId);

    Integer updateStatusAndPayTime(@Param("shopId") Integer shopId, @Param("orderId") String orderId ,@Param("payTime")Date payTime);

    //菜品销售排行 天
    List<Dish> dishHot(@Param("shopId") Integer shopId, @Param("startTime") String startTime,@Param("endTime") String endTime);

    //菜品销售排行  周
    List<Dish> dishHotWeek(@Param("shopId") Integer shopId, @Param("startTime") String startTime,@Param("endTime") String endTime);

    //菜品销售排行  月
    List<Dish> dishHotMonth(@Param("shopId") Integer shopId, @Param("startTime") String startTime,@Param("endTime") String endTime);

    //总收入 天
    Boss maxInCome(@Param("shopId") Integer shopId, @Param("startTime") String startTime, @Param("endTime") String endTime);

    //总收入 周
    Boss maxInComeWeek(@Param("shopId") Integer shopId, @Param("startTime") String startTime, @Param("endTime") String endTime);

    //总收入 月
    Boss maxInComeMonth(@Param("shopId") Integer shopId, @Param("startTime") String startTime, @Param("endTime") String endTime);

    //通过orderId一键修改全部的订单状态信息
    Integer  changeStatusByOrderId(@Param("status")Integer status,@Param("shopId") Integer shopId,@Param("orderId") String orderId);

    //单个修改订单状态
    Integer  changeStatusByOrderIdAndDishId(@Param("status")Integer status,@Param("shopId") Integer shopId,@Param("dishId")Integer dishId,@Param("orderId") String orderId);

    //修改退菜总价为0
    Integer  change0(@Param("shopId") Integer shopId,@Param("dishId")Integer dishId,@Param("orderId") String orderId);

    //查询菜品信息是否存在
    Integer  checkExist(@Param("shopId") Integer shopId,@Param("dishId")Integer dishId,@Param("orderId") String orderId);

    //获取菜品数量
    Integer  getDishNumber(@Param("shopId") Integer shopId,@Param("dishId")Integer dishId,@Param("orderId") String orderId);

    //更新菜品数量
    Integer  updateNumber(@Param("number")Integer number,@Param("totalPrice")Double totalPrice,@Param("shopId") Integer shopId,@Param("dishId")Integer dishId,@Param("orderId") String orderId);

    //查看订单菜品的状态
    List<Order> selectStatus(@Param("orderId") String oderId, @Param("shopId") Integer sid);

    //以餐桌的Id查询订单
    List<Order> selectByTableId(@Param("shopId")Integer shopId,@Param("tableId")Integer tableId);


    //加菜
    Integer addDish(Order order);

    String getOrderIdByTableId(@Param("tableId") Integer tableId,@Param("shopId")Integer shopId);
    //通过shopId
    List<Order> findAll(int shopId);
    //取消订单
    Integer delectorder(Integer orderId);
}
