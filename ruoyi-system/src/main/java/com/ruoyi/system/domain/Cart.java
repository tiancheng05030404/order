package com.ruoyi.system.domain;

import lombok.Data;

@Data
public class Cart {

	Integer id;
	Integer shopId;
	Integer dishId;
	String  dishName;
	Double dishPrice;
	String dishImage;
	Integer number;
	Integer  tableId;
    Double totalPrice;
}
