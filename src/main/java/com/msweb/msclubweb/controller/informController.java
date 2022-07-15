package com.msweb.msclubweb.controller;


import com.alibaba.druid.util.StringUtils;
import com.msweb.msclubweb.common.Result;
import com.msweb.msclubweb.domain.Inform;
import com.msweb.msclubweb.domain.News;
import com.msweb.msclubweb.service.InformService;
import com.msweb.msclubweb.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/inform")
public class informController {
    @Autowired
    private InformService informService;

    //公告表(通过路由id来写)
    //1.添加公告
    @PostMapping("/add")
    public Result<Inform> addInform(@RequestBody Inform inform) {
        //1.数据检验
        if (StringUtils.isEmpty(inform.getIntroduction()))
            return Result.dataError();
        //2.添加
        return informService.addInform(inform);
    }

    //2.删除公告
    @PostMapping("del/{id}")
    public Result<Inform> delInform(@PathVariable("id") Integer id) {
        //1.验证
        if (id == null) return Result.dataError();
        //2.删除
        return informService.deleteById(id);
    }

    //3.查看通过
    @PostMapping("/view/{id}")
    public Result<Inform> viewInform(@PathVariable("id") Integer id) {
        //1.验证
        if (id == null) return Result.dataError();
        //2.查看
        return informService.selectById(id);
    }

    //4.分页模糊查询
    @PostMapping("/find")
    public Result<Map<String, Object>> findInform(@RequestBody Map<String,Object> map) {
        //首次访问时请求体不用加那一页默认第一页
        //默认第1页，每页10条
        String condition = (String) map.get("condition");
        Long currentPage = Long.valueOf((Integer) map.get("currentPage"));
        Long pageSize = Long.valueOf((Integer) map.get("pageSize"));
        Map<String, Object> informPage = informService.selectPage(currentPage, pageSize, condition);
        return Result.success(informPage);
    }
}
