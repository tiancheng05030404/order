package com.ruoyi.system.service;



import com.ruoyi.system.domain.Cart;
import com.ruoyi.system.domain.CartVO;
import org.omg.CORBA.INTERNAL;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Map;


/**
 * 处理购物车数据的业务层接口
 */
public interface ICartService {


	int  addToCart(Cart cart);

	Integer checkUniqueness(Integer shopId,Integer dishId,Integer tableId);
	
	List<Cart> getByTableId(Integer  tableId ,Integer shopId);

	Map<String,Object> selectNumberAndPrice(Integer  tableId ,Integer shopId);

	Integer updateNumber(Cart cart);

	Integer deleteByCid(Cart cart);

	Integer deleteByCids(CartVO cart);



	
}






