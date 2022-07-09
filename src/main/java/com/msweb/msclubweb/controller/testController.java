package com.msweb.msclubweb.controller;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.msweb.msclubweb.common.PageParam;
import com.msweb.msclubweb.common.R;
import com.msweb.msclubweb.common.Result;
import com.msweb.msclubweb.domain.*;
import com.msweb.msclubweb.service.*;
import com.msweb.msclubweb.util.PatternHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class testController {
    @Autowired
    private InformService informService;
    @Autowired
    private DepartmentService departmentService;
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


    //部门管理表(通过name)
    //1.添加
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
    //2.删除部门管理
    @PostMapping("/department/del")
    public Result<Department> delDepartment(@RequestBody Department department){
        //数据验证
        //1.不为空
        if(StringUtils.isEmpty(department.getName()))
            return Result.dataError();
        return departmentService.deleteByName(department);
    }
    //3.更新
    @PostMapping("/department/update")
    public Result<Department> updateDepartment(@RequestBody Department department){
        //1.数据验证,不为空和格式问题
        if(StringUtils.isEmpty(department.getName())||
                StringUtils.isEmpty(department.getEmail())||
                StringUtils.isEmpty(department.getQq()) ||
                !PatternHelper.checkMail(department.getEmail())||
                !PatternHelper.checkQq(department.getQq())) return Result.dataError();
        //2.更新
        return departmentService.updateInfo(department);
    }
    //4.查看
    @PostMapping("/department/view")
    public Result<Department> viewDepartment(@RequestBody Department department){
        //1.数据验证
        if(department.getFlag()==null) return Result.dataError();
        //2.查找
        return departmentService.findByFlag(department);
    }

    //公告表(通过时间戳来写)
    //1.添加公告
    @PostMapping("/inform/add")
    public Result<Inform> addInform(@RequestBody Inform inform){
        //1.数据检验
        if (StringUtils.isEmpty(inform.getTitle())||
                StringUtils.isEmpty(inform.getIntroduction()))
            return Result.dataError();
        //2.添加
        return informService.addInform(inform);
    }
    //2.删除公告
    @PostMapping("/inform/del")
    public Result<Inform> delInform(@RequestBody Inform inform){
        //1.验证
        if(StringUtils.isEmpty(inform.getTitle()))
            return Result.dataError();
        //2.删除
        return informService.deleteByTime(inform);
    }
    //3.查看通过
    @PostMapping("/inform/view")
    public Result<Inform> viewInform(@RequestBody Inform inform){
        //1.验证
        if(StringUtils.isEmpty(inform.getTitle()))
            return Result.dataError();
        //2.删除
        return informService.deleteByTime(inform);
    }
    //4.分页模糊查询
    @PostMapping("/inform/find")
    public Result<Page<Inform>> findInform(@RequestBody PageParam<Inform> pageParam){
        //首次访问时请求体不用加那一页默认第一页
        Page<Inform> informPage = informService.selectPage(pageParam, pageParam.getData());
        return Result.success(informPage);
    }

    //img
    //1.添加图片
    @PostMapping("/img/add")
    public Result<Imags> addImg(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        return imagsService.addImg(file);
    }
    //新闻(时间戳)
    //1.提交新闻
    @PostMapping("/news/add")
    public Result<News> addNews(@RequestBody News news){
        //1.检验数据
        if(StringUtils.isEmpty(news.getTitle())||
        StringUtils.isEmpty(news.getIntroduction())||
        news.getAuthors().size()==0)
            return Result.dataError();
        //2.添加新闻
        int newsId = newsService.addNews(news);
        if(newsId==-1) return Result.sqlError();
        //3.插入author
        Map<String, Object> map = authorsService.addAuthor(news.getAuthors());
        int flag1 = (int)map.get("code");
        if(flag1!=200){
            if(flag1==500){
                return Result.sqlError();
            }//后面是留给以后其他情况如要检测作者姓名格式不对等等
        }
        List<Integer> authorIds = (List<Integer>) map.get("authorIds");
        //4.建立联系
        int flag2 = newsAuthorService.addByNewsidAndAuthorid(newsId, authorIds);
        if(flag2==200) return Result.success(null);
        else{
            return Result.sqlError();//以后有其他情况在里面加
        }
    }
    //2.查看新闻，主要通过时间戳找新闻
    @PostMapping("/news/view")
    public Result<News> viewNew(@RequestBody News news){
        //1.验证
        if(news.getTime()==null) return Result.dataError();
        //2.查找
        return newsService.selectByTime(news);
    }
    //3.
}
