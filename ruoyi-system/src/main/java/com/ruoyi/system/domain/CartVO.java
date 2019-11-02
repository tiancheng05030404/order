package com.ruoyi.system.domain;

import lombok.Data;

@Data
public class CartVO {

	Integer[] id;
	Integer sid;
	String  dishName;
	Double dishPrice;
	String dishImage;
	Integer number;
	String  openId;

}
