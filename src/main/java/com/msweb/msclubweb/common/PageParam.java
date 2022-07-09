package com.msweb.msclubweb.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * 分页查询参数
 */

@Data
public class PageParam <T>{
    /**
     * 那一页默认第一页
     */
//    @Value("${pageParam.page}")
    private long page = 1;
    /**
     * 每页条数默认为10
     */
//    @Value("${pageParam.size}")
    private long size = 10;
//这里主要是为了接收模糊查询条件
    /**
     *请求体
     *
     {
        “pageParam”:{
            "size":""
            "page":""
            "data":{
                里面封装数据表实体模糊查询条件即可
            }
        }
     }
     */
    private T data;
}