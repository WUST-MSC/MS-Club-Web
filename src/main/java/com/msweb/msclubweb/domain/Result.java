package com.msweb.msclubweb.domain;

public class Result {
    private String data;
    private Integer code;

    public Result(Integer code,String data) {
        this.data = data;
        this.code = code;
    }

    public Result(Integer code) {
        this.code = code;
    }
    public Result() {
    }


    public Object getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}

