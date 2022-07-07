package com.msweb.msclubweb.controller;


import com.msweb.msclubweb.domain.*;
import com.msweb.msclubweb.service.AdministratorService;
import com.msweb.msclubweb.service.HonorService;
import com.msweb.msclubweb.service.RecruitmentService;
import com.msweb.msclubweb.service.WorksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    HonorService honorService;

    @Autowired
    AdministratorService administratorService;

    @Autowired
    RecruitmentService recruitmentService;

    @Autowired
    WorksService worksService;

    //高层管理员部分
    @RequestMapping("/Administrator")

    @PostMapping("/add")
    public Result AddMessage(@RequestBody Administrator administrator){
        return new Result(administratorService.AddAdministrator(administrator));
    }

    @PostMapping("/deleteById{id}")
    public Result deleteMessage(Integer id){
        return new Result(administratorService.deleteById(id));
    }

    @PostMapping("/All")
    public Result selectAllAdministrator()
    {
        Integer flag;
        if(administratorService.selectAll().size()==0) flag=500;
        else flag=200;
        return new Result(flag);
    }

    //荣誉部分
    @RequestMapping("/honor")

    @PostMapping("/searchByPage")
    public Result getAllHonor(Integer HonorPage) {
        BackPage<Honor> message=honorService.selectPage(HonorPage);
        Integer flag;
        if (message.getContentList().size()==0) flag=500;
        else flag=200;
        return new Result(flag);
    }

    @PostMapping("/add")
    public Result addHonor(Honor honor)
    {
        Integer flag=honorService.AddHonor(honor);
        return new Result(flag);
    }

    @PostMapping("/deleteById{id}")
    public Result deleteHonor(Integer id){
        Integer flag=honorService.deleteById(id);
        return new Result(flag);
    }

    @PostMapping("/AllHonor")
    public Result selectAll()
    {
        Integer flag;
        if(honorService.selectAll().size()==0) flag=500;
        else flag=200;
        return new Result(flag);
    }



}
