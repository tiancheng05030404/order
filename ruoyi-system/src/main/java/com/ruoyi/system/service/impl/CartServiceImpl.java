package com.ruoyi.system.service.impl;

import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.List;
import java.util.Map;


import com.ruoyi.system.domain.Cart;
import com.ruoyi.system.domain.CartVO;
import com.ruoyi.system.mapper.CartMapper;
import com.ruoyi.system.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 处理购物车数据的业务层实现类
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class CartServiceImpl implements ICartService {
	
	@Autowired
	private CartMapper cartMapper;

	@Override
	public int addToCart(Cart cart){
     return  cartMapper.addnew(cart);
	}

	@Override
	public Integer checkUniqueness(Integer shopId, Integer dishId,Integer tableId) {
		return cartMapper.checkUniqueness(shopId,dishId,tableId);
	}

	@Override
	public List<Cart> getByTableId(Integer tableId ,Integer shopId) {
		return cartMapper.findByTabled(tableId,shopId);
	}

	@Override
	public Map<String, Object> selectNumberAndPrice(Integer tableId, Integer shopId) {
		return cartMapper.selectNumberAndPrice(tableId,shopId);
	}

	@Override
	public Integer updateNumber(Cart cart) {
		return cartMapper.updateNumber(cart);
	}

	@Override
	public Integer deleteByCid(Cart cart) {
		return cartMapper.deleteByCid(cart);
	}

	@Override
	public Integer deleteByCids(CartVO cartVO) {
		return cartMapper.deleteByCids(cartVO);
	}

}


