package com.msweb.msclubweb.common;

public enum ResultCodeEnum {
    SUCCESS(200 , "Success"),//操作成功
    NOTFOUND(404,"Not Found"),//未找到目标数据
    SQLERROR(500,"Sql Error"),//sql语句错误
    DATAERROR(400,"The data format is incorrect or empty");//数据格式有问题

    private Integer code;
    private String msg;

    ResultCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
