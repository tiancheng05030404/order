package com.ruoyi.system.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;


import com.ruoyi.system.domain.Cart;
import com.ruoyi.system.domain.CartVO;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.INTERNAL;


/**
 * 处理购物车数据的持久层接口
 */
public interface CartMapper {

	/**
	 * 增加新的购物车数据
	 * @param cart 购物车数据
	 * @return 受影响的行数
	 */
	Integer addnew(Cart cart);

	Integer checkUniqueness(@Param("shopId")Integer shopId,@Param("dishId")Integer dishId,@Param("tableId")Integer tableId);

	Cart returnId(@Param("shopId")Integer shopId,@Param("dishId")Integer dishId,@Param("tableId")Integer tableId);

	List<Cart> findByTabled(@Param("tableId") Integer tableId,@Param("shopId")Integer shopId);

	Map<String,Object> selectNumberAndPrice(@Param("tableId") Integer tableId,@Param("shopId")Integer shopId);

	Integer updateNumber(Cart cart);

	Integer deleteByCid(Cart cart);

	Integer deleteByCids(CartVO cartVO);

	//返回购物车的全部总价
	Integer getTotalPrice(@Param("shopId")Integer shopId,@Param("tableId")Integer tableId);
}





