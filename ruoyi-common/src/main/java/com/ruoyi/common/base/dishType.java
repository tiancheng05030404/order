package com.ruoyi.common.base;


public enum dishType {

    issue(0, "已出单"),
    doing(1, "正在做"),
    serving(2, "已上菜"),
    countermand(3,"已退菜"),
    paid(4, "已付款");

    dishType(Integer code, String info) {
        this.code = code;
        this.info = info;
    }

    private final Integer code;
    private final String info;
}
