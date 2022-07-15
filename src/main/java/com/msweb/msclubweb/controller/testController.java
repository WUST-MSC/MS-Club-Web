package com.msweb.msclubweb.controller;

import com.alibaba.druid.util.StringUtils;
import com.msweb.msclubweb.common.Result;
import com.msweb.msclubweb.domain.*;
import com.msweb.msclubweb.service.*;
import com.msweb.msclubweb.util.PatternHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.*;

@RestController
public class testController {
    @Autowired
    private InformService informService;
    @Autowired
    private ImagsService imagsService;
    @Autowired
    private AuthorsService authorsService;
    @Autowired
    private NewsAuthorService newsAuthorService;
    @Autowired
    private NewsImagsService newsImagsService;
    @Autowired
    private NewsService newsService;

    //测试端口
    @PostMapping("/test")
    public Result<String> test() {

        return Result.success(null);
    }

   /* //部门管理表(通过flag)
    //1.添加（用于初始化部门时添加）
    @PostMapping("/department/add")
    public Result<Department> addDepartment(@RequestBody Department department){
        //数据验证数据（指不为空之类的）
        //1.不为空和格式
        if(StringUtils.isEmpty(department.getName())||
                StringUtils.isEmpty(department.getEmail())||
                StringUtils.isEmpty(department.getQq()) ||
                !PatternHelper.checkMail(department.getEmail())||
        !PatternHelper.checkQq(department.getQq())) return Result.dataError();
        //调用service方法
        return departmentService.addDepartment(department);
    }

    //2.删除部门管理==删除部门(通过flag删除)
    @PostMapping("/department/del")
    public Result<Department> delDepartment(@RequestBody Department department){
        //数据验证
        //1.不为空
        if(department.getFlag()==null||department.getFlag()<0)
            return Result.dataError();
        return departmentService.deleteByFlag(department);
    }

    //3.更新（通过flag更新）（应该属于后台管理用到的）
    @PostMapping("/department/update")
    public Result<Department> updateDepartment(@RequestBody Department department){
        //1.数据验证,不为空和格式问题
        if(StringUtils.isEmpty(department.getName())||
                StringUtils.isEmpty(department.getEmail())||
                StringUtils.isEmpty(department.getQq()) ||
                department.getFlag()==null||department.getFlag()<0 ||
                !PatternHelper.checkMail(department.getEmail())||
                !PatternHelper.checkQq(department.getQq())) return Result.dataError();
        //2.更新
        return departmentService.updateInfo(department);
    }

    //4.查看,对应部门
    @PostMapping("/department/view")
    public Result<Department> viewDepartment(@RequestBody Department department){
        //1.数据验证
        if(department.getFlag()==null) return Result.dataError();
        //2.查找
        return departmentService.findByFlag(department);
    }*/




    //img
    //1.添加图片
    @PostMapping("/img/add")
    public Result<Imags> addImg(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        return imagsService.addImg(file);
    }
}
