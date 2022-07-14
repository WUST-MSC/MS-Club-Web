package com.msweb.msclubweb.controller;


import com.msweb.msclubweb.domain.*;
import com.msweb.msclubweb.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


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

    @Autowired
    ImagsService imagsService;

    //添加图片
    @PostMapping("/img/add")
    public Result addImg(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        return  imagsService.addImg(file);
    }

    //高层管理员部分
    //添加高层信息
    @PostMapping("/Administrator/add")
    public Result addAdministrator(@RequestBody Administrator administrator){
        int flag=administratorService.AddAdministrator(administrator);
        if(flag==1) return new Result(200, "添加成功！");
        return new Result(500,"添加失败!");
    }

    //按Name删除高层信息
    @PostMapping("/Administrator/deleteByName")
    public Result deleteAdministratorById(@RequestBody Administrator administrator){
        int flag=administratorService.deleteByName(administrator);
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
    //分页分类查看荣誉
    @PostMapping("/honor/searchByPage")
    public Result getPageHonor(@RequestBody PageSelect pageSelect) {
        BackPage<Honor> PageMessage=honorService.selectPage(pageSelect);
        if (PageMessage.getContentList().isEmpty()) return new Result(500,"查看失败！");
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

    //按照Title删除荣誉
    @PostMapping("/honor/deleteByTitle")
    public Result deleteHonorById(@RequestBody Honor honor){
        int flag=honorService.deleteByTitle(honor);
        if (flag==1) return new Result(200,"删除成功");
        else return new Result(500,"删除失败");
    }

    //获取所有荣誉
    @PostMapping("/honor/AllHonor")
    public Result getAllHonor()
    {
        List<Honor> message=honorService.selectAll();
        if(message.size()==0) return new Result(500,"查询失败");
        else return new Result(200," 查询成功",message);
    }


    //按照Title查询荣誉
    @PostMapping("/honor/selectByTitle")
    public Result honorSelectById(Honor honor)
    {
        Honor honorMessage=honorService.selectByTitle(honor);
        if(honorMessage.getId()==null) return new Result(500,"获取失败！");
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
    //添加报名信息(如果学号相同，则点击确定时询问是否修改，确定后根据学号更改数据）
    @PostMapping("/Recruitment/add")
    public  Result addRecruitment(@RequestBody Recruitment recruitment,String updateKey)
    {
        if(updateKey.equals("Identify the changes"))//先添加再删除（根据原来数据的id）原来的数据
        {
            int id=recruitmentService.selectByS_Id(recruitment).getId();
            int flag=recruitmentService.AddRecruitment(recruitment);
            if(flag==0) return new Result(500,"修改失败");
            flag=recruitmentService.deleteById(id);
            if(flag>0) return new Result(200,"修改成功");
            return  new Result(500,"系统繁忙");
        }
        //将先传来的数据与原有数据对比
        if(recruitmentService.SearchSame(recruitment)==1) return new Result(200,"数据重复");
        else
        {
            int flag = recruitmentService.AddRecruitment(recruitment);
            if (flag == 1) return new Result(200, "添加成功！");
            return new Result(500, "添加失败!");
        }
    }

    //按email删除报名信息
    @PostMapping("/Recruitment/deleteByEmail")
    public  Result deleteRecruitmentById(@RequestBody Recruitment recruitment)
    {
        int flag=recruitmentService.deleteByEmail(recruitment);
        return flag==1?new Result(200,"删除成功"):new Result(500,"删除失败");
    }

    //按学号删除
    @PostMapping("/Recruitment/deleteByStudentId")
    public  Result deleteRecruitmentByStudentId(@RequestBody Recruitment recruitment){
        int flag=recruitmentService.deleteByS_Id(recruitment);
        return flag==1? new Result(200,"删除成功"):new Result(500,"删除失败");
    }
    //获取报名信息
    @PostMapping("/Recruitment/All")
    public  Result getAllRecruitment()
    {
        List<Recruitment> message=recruitmentService.selectAll();
        if(message.size()==0) return new Result(500,"查询失败");
        else return new Result(200," 查询成功",message);
    }

    //按照email查询报名信息
    @PostMapping("/Recruitment/selectByEmail")
    public  Result recruitmentSelectByStudent_id(@RequestBody Recruitment recruitment)
    {
        Recruitment recruitmentMessage=recruitmentService.selectByS_Id(recruitment);
        if(recruitmentMessage.getId()==0) return new Result(500,"查询失败!");
        else return new Result(200,"查询成功",recruitment);

    }

    //模糊查询
    @PostMapping("/Recruitment/findByKey")
    public Result recruitmentByKey(@RequestBody PageSelect pageSelect,String key)
    {
        BackPage<Recruitment> PageMessage=recruitmentService.selectByKey(pageSelect,key);
        if(PageMessage.getContentList().isEmpty()) return new Result(500,"查看失败！");
        else return new Result(200,"查询成功",PageMessage);
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

    //根据Title删除项目作品
    @PostMapping("/Works/deleteByTitle")
    public  Result deleteWorksById(@RequestBody Works works)
    {
        int flag=worksService.deleteByTitle(works);
        if (flag==200) return new Result(200,"删除成功");
        else return new Result(500,"删除失败");
    }

    //按Title查看项目作品
    @PostMapping("/Works/selectByTitle")
    public  Result selectWorksById(@RequestBody Works works)
    {
        Works worksMessage=worksService.selectByTitle(works);
        if(worksMessage.getId()==0) return new Result(500,"查询失败!");
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
