package com.msweb.msclubweb.controller;


import com.baomidou.mybatisplus.extension.api.R;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    //添加高层信息
    @PostMapping("/Administrator/add")
    public Result addAdministrator(@RequestBody Administrator administrator){
        int flag=administratorService.AddAdministrator(administrator);
        if(flag==1) return new Result(200, "添加成功！");
        return new Result(500,"添加失败!");
    }

    //按id删除高层信息
    @PostMapping("/Administrator/deleteById")
    public Result deleteAdministratorById(@RequestBody Administrator administrator){
        int flag=administratorService.deleteById(administrator);
        if(flag==1) return new Result(200,"删除成功!");
        return new Result(500,"删除失败！");
    }

    //获取所有高层信息
    @PostMapping("/Administrator/all")
    public Result getAllAdministrator()
    {
        List <Administrator> message =administratorService.selectAll();
        if(message.size()==0) return new Result(500,"查询失败！");
        else return new Result(200,"查询成功",message);
    }

    //
    @PostMapping("/Administrator/update")
    public  Result updateAdministrator(@RequestBody Administrator administrator)
    {
        Integer flag=administratorService.Update(administrator);
        if(flag==1) return new Result(200,"修改成功!");
        return new Result(500,"修改失败！");
    }

    //荣誉部分
    //分页查看荣誉
    @PostMapping("/honor/searchByPage")
    public Result getPageHonor(@RequestBody Integer HonorPage) {
        BackPage<Honor> PageMessage=honorService.selectPage(HonorPage);
        if (PageMessage.getContentList().size()==0) return new Result(500,"查看失败！");
        else return new Result(200,"查询成功",PageMessage);
    }

    //添加荣誉
    @PostMapping("/honor/add")
    public Result addHonor(@RequestBody Honor honor)
    {
        int flag=honorService.AddHonor(honor);
        if (flag==1) return new Result(200,"添加成功");
        else return new Result(500,"添加失败");
    }

    //按照id删除荣誉
    @PostMapping("/honor/deleteById")
    public Result deleteHonorById(@RequestBody Honor honor){
        int flag=honorService.deleteById(honor);
        if (flag==1) return new Result(200,"删除成功");
        else return new Result(500,"删除失败");
    }

    //获取所有荣誉
    @PostMapping("/honor/AllHonor")
    public Result getAllHonor()
    {
        List<Honor> message=honorService.selectAll();
        int flag;
        if(message.size()==0) return new Result(500,"查询失败");
        else return new Result(200," 查询成功",message);
    }

    //按照flag查询团体/个人荣誉
    @PostMapping("/honor/flag")
    public Result selectHonorByFlag(@RequestBody Integer flag)
    {
        List<Honor> message=honorService.selectByFlag(flag);
        if(message.isEmpty()) return new Result(500,"查询失败");
        else return new Result(200,"查询成功",message);
    }

    //按照id查询荣誉
    @PostMapping("/honor/selectById")
    public Result honorSelectById(Integer id)
    {
        Honor honor=honorService.selectById(id);
        if(honor.getId()==null) return new Result(500,"获取失败！");
        else return new Result(200,"获取成功",honor);
    }

    //
    @PostMapping("/honor/update")
    public  Result updateHonor(@RequestBody Honor honor)
    {
        Integer flag=honorService.Update(honor);
        if(flag==1) return new Result(200,"修改成功!");
        return new Result(500,"修改失败！");
    }


    //报名表部分
    //添加报名信息
    @PostMapping("/Recruitment/add")
    public  Result addRecruitment(@RequestBody Recruitment recruitment)
    {
        int flag=recruitmentService.AddRecruitment(recruitment);
        if(flag==1) return new Result(200, "添加成功！");
        return new Result(500,"添加失败!");
    }

    //按id删除报名信息
    @PostMapping("/Recruitment/deleteById")
    public  Result deleteRecruitmentById(@RequestBody Recruitment recruitment)
    {
        int flag=recruitmentService.deleteBtId(recruitment);
        if (flag==1) return new Result(200,"删除成功");
        else return new Result(500,"删除失败");
    }

    //获取报名信息
    @PostMapping("/Recruitment/All")
    public  Result getAllRecruitment()
    {
        List<Recruitment> message=recruitmentService.selectAll();
        int flag;
        if(message.size()==0) return new Result(500,"查询失败");
        else return new Result(200," 查询成功",message);
    }

    //按照学号查询报名信息
    @PostMapping("/Recruitment/selectByS_id")
    public  Result recruitmentSelectByStudent_id(@RequestBody Integer student_id)
    {
        Recruitment recruitment=recruitmentService.selectByStudent_id(student_id);
        if(recruitment.getId()==0) return new Result(500,"查询失败!");
        else return new Result(200,"查询成功",recruitment);

    }




    //项目作品部分
    //添加项目作品
    @PostMapping("/Works/add")
    public  Result addWorks(@RequestBody Works works)
    {
        int flag=worksService.addWorks(works);
        if (flag==200) return new Result(200,"添加成功");
        else return new Result(500,"添加失败");
    }

    //删除项目作品
    @PostMapping("/Works/deleteById")
    public  Result deleteWorksById(@RequestBody Works works)
    {
        int flag=worksService.deleteById(works);
        if (flag==200) return new Result(200,"删除成功");
        else return new Result(500,"删除失败");
    }

    //按id查看项目作品
    @PostMapping("/Works/selectById")
    public  Result selectWorksById(@RequestBody Integer id)
    {
        Works works=worksService.selectById(id);
        if(works.getId()==0) return new Result(500,"查询失败!");
        else return new Result(200,"查询成功",works);
    }

    //按照flag查看项目或作品
    @PostMapping("/Works/selectByFlag")
    public  Result selectWorksByFlag(@RequestBody Integer flag)
    {
        List<Works> message=worksService.selectByFlag(flag);
        if(message.isEmpty()) return new Result(500,"获取失败");
        else return new Result(200,"获取成功",message);
    }

    //获取项目作品
    @PostMapping("/Works/selectAll")
    public Result getAllWorks()
    {
        List<Works> message=worksService.selectAll();
        if(message.isEmpty()) return new Result(500,"获取失败！");
        return new Result(200,"获取成功" ,message);
    }

    //
    @PostMapping("/Works/update")
    public  Result updateWorks(@RequestBody Works works)
    {
        Integer flag=worksService.Update(works);
        if(flag==1) return new Result(200,"修改成功!");
        return new Result(500,"修改失败！");
    }
}
