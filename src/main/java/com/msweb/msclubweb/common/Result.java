package com.msweb.msclubweb.common;

import lombok.Data;

@Data
public class Result <T>{
    /**
     * 状态码 ----  见状态码enum
     */
    private int code;
    /**
     * 数据实体
     */
    private T data;
    /**
     * 信息
     */
    private String msg;

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<T>();
        result.setData(data);
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMsg(ResultCodeEnum.SUCCESS.getMsg());
        return result;
    }

    public static <T> Result<T> sqlError() {
        Result<T> result = new Result<T>();
        //result.setData(data);
        result.setCode(ResultCodeEnum.SQLERROR.getCode());
        result.setMsg(ResultCodeEnum.SQLERROR.getMsg());
        return result;
    }

    public static <T> Result<T> dataError() {
        Result<T> result = new Result<T>();
        result.setCode(ResultCodeEnum.DATAERROR.getCode());
        result.setMsg(ResultCodeEnum.DATAERROR.getMsg());
        return result;
    }

    public static <T> Result<T> notFound() {
        Result<T> result = new Result<T>();
        result.setCode(ResultCodeEnum.NOTFOUND.getCode());
        result.setMsg(ResultCodeEnum.NOTFOUND.getMsg());
        return result;
    }
}
