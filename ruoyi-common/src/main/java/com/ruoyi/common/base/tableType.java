package com.ruoyi.common.base;


public enum tableType {

    issue(0, "空闲"),
    doing(1, "开桌"),
    serving(2, "用餐"),
    countermand(3,"待清理"),
    paid(4, "已付款");

    tableType(Integer code, String info) {
        this.code = code;
        this.info = info;
    }

    private final Integer code;
    private final String info;
}
