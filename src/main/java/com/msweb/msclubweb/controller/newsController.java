package com.msweb.msclubweb.controller;


import com.alibaba.druid.util.StringUtils;
import com.msweb.msclubweb.common.Result;
import com.msweb.msclubweb.domain.News;
import com.msweb.msclubweb.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/news")
public class newsController {
    @Autowired
    private NewsService newsService;

    //新闻
    //1.添加新闻
    @PostMapping("/add")
    public Result<News> addNews(@RequestBody News news){
        if(news.getAuthors().size()==0||
                StringUtils.isEmpty(news.getTitle())||
                StringUtils.isEmpty(news.getIntroduction()))
            return Result.dataError();
        return newsService.addNews(news);
    }

    //2.查看新闻，主要通过时间戳找新闻
    @PostMapping("/view/{id}")
    public Result<News> viewNew(@PathVariable("id") Integer id){
        //1.查找
        return newsService.selectById(id);
    }

    //3.删除新闻
    @PostMapping("/del/{id}")
    public Result<News> delNews(@PathVariable("id") Integer id){

        return newsService.deleteById(id);
    }
    //4.分页模糊查询
    @PostMapping("/find")
    public Result<Map<String,Object>> findNews(@RequestBody Map<String,Object> map){
        //首次访问时请求体不用加那一页默认第一页
        //默认第1页，每页10条
        String condition = (String) map.get("condition");
        Long currentPage = Long.valueOf((Integer) map.get("currentPage"));
        Long pageSize = Long.valueOf((Integer) map.get("pageSize"));
        Map<String, Object> newsPage = newsService.selectPage(currentPage, pageSize, condition);
        return Result.success(newsPage);
    }
}
