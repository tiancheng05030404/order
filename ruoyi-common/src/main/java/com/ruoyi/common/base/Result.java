package com.ruoyi.common.base;


import lombok.Data;
import net.sf.json.JSON;

@Data
public class Result {
    private Integer code;
    private JSON data;
    private String errMsg;



    public Result(Integer code, JSON data) {
        this.code = code;
        this.data = data;
    }

    public Result(Integer code, String errMsg) {
        this.code = code;
        this.errMsg = errMsg;
    }

    public Result(Integer code, JSON data, String errMsg) {
        this.code = code;
        this.data = data;
        this.errMsg = errMsg;
    }
}
