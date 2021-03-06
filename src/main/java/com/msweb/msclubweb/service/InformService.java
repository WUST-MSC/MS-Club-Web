package com.msweb.msclubweb.service;

import com.alibaba.druid.sql.ast.statement.SQLForeignKeyImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.msweb.msclubweb.domain.Inform;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author 86189
* @description 针对表【inform】的数据库操作Service
* @createDate 2022-07-07 09:24:47
*/
public interface InformService extends IService<Inform> {
    //1.写通知
    int addInform(Inform inform);

    //2.查看通知 实际上是通过id(因为有重名公告)找到对应的inform，IService自带getById
    Map<String, Object> selectByTitle(Inform inform);

    //3.删除通知 也是通过id 自带
    int deleteByTitle(Inform inform);

    //上面两个好像不需要逻辑判断直接用就行

    //4.分页模糊查询公告
    Map<String,Object> selectPage(long currentPage, long pageSize,Inform inform);
}
