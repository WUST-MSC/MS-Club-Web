package com.msweb.msclubweb.service;

import com.msweb.msclubweb.common.Result;
import com.msweb.msclubweb.domain.Inform;
import com.msweb.msclubweb.domain.News;
import com.baomidou.mybatisplus.extension.service.IService;

import java.rmi.MarshalledObject;
import java.util.Map;

/**
* @author 86189
* @description 针对表【news】的数据库操作Service
* @createDate 2022-07-07 09:26:05
*/
public interface NewsService extends IService<News> {
    //添加新闻 返回newsId
    //int addNews(News news);
    Result<News> addNews(News news);
    //删除新闻
    Result<News> deleteById(Integer id);
    //查看新闻
    Result<News> selectById(Integer id);
    //4.分页模糊查询公告
    Map<String,Object> selectPage(Long currentPage, Long pageSize, String condition);
}
