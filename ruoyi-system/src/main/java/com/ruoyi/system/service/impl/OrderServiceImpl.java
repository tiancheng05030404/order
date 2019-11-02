package com.ruoyi.system.service.impl;

import java.util.Date;
import java.util.List;


import com.ruoyi.system.domain.Order;
import com.ruoyi.system.mapper.OrderMapper;
import com.ruoyi.system.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;


/**
 * 处理订单相关数据的业务层实现类
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class OrderServiceImpl implements IOrderService {
	
	@Autowired
	private OrderMapper orderMapper;

	@Override
	public int addNew(Order order) {
		return orderMapper.addNew(order);
	}

	@Override
	public Integer updateOrderTime(Integer shopId, String orderId,Date orderTime) {
		return orderMapper.updateOrderTime(shopId,orderId,orderTime);
	}

	@Override
	public List<Order> selectByOrderId(String orderId,Integer shopId) {
		return orderMapper.selectByOrderId(orderId,shopId);
	}

	@Override
	public List<Order> selectDesc(String openId, Integer shopId) {
		return orderMapper.selectDesc(openId,shopId);
	}

	@Override
	public List<Order> getList(String openId,Integer sid) {
		return orderMapper.getList(openId,sid);
	}


	@Override
	public int deleteByTableId(Integer tableId,Integer shopId) {
		return orderMapper.deleteByTableId(tableId,shopId);
	}

	@Override
	public int updateStatusAndPayTime(Integer shopId, String orderId, Date payTime) {
		return orderMapper.updateStatusAndPayTime(shopId,orderId,payTime);
	}


	@Override
	public Integer changeStatusByOrderId(Integer status,Integer shopId, String orderId) {
		return orderMapper.changeStatusByOrderId(status,shopId,orderId);
	}

	@Override
	public Integer changeStatusByOrderIdAndDishId(Integer status, Integer shopId, Integer dishId, String orderId) {
		return orderMapper.changeStatusByOrderIdAndDishId(status,shopId,dishId,orderId);
	}

	@Override
	public List<Order> selectStatus(String orderId, Integer shopId) {
		return orderMapper.selectStatus(orderId,shopId);
	}

	@Override
	public List<Order> selectByTableId(Integer shopId, Integer tableId) {
		return orderMapper.selectByTableId(shopId,tableId);
	}

	@Override
	public Integer addDish(Order order) {
		return orderMapper.addDish(order);
	}

	@Override
	public String getOrderIdByTableId(Integer tableId, Integer shopId) {
		return orderMapper.getOrderIdByTableId(tableId,shopId);
	}
	@Override
	public List<Order> finAll(Integer shopId) {
		return orderMapper.findAll(shopId);
	}
	@Override
	public Integer deleteorder(Integer orderId){

		return orderMapper.delectorder(orderId);
	}

}
