package com.msweb.msclubweb.controller;

import com.msweb.msclubweb.vo.R;
import com.msweb.msclubweb.domain.*;
import com.msweb.msclubweb.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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


    //图片上传路径
    @Value("${file.upload.path}")
    private String path;

    /*@PostMapping("/test")
    public R test(@RequestBody Map<String,Object> map){
        System.out.println(map);
        List<Authors> authors = (List<Authors>) map.get("[]");
        System.out.println(authors);
        R r = new R();
        ArrayList<Authors> list = new ArrayList<>();
        list.add(new Authors());
        list.add(new Authors());
        r.setDate(list);
        return r;
    }*/

    //news-img
    //1.上传图片(测试中)
    @PostMapping("/img/add")
    public R imgAdd(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        R r = new R();
        //1.保存图片
        //获取文件在服务器的储存位置
        File filePath = new File(path);
        if(!filePath.exists() && !filePath.isDirectory()){
            //文件夹不存在创建文件夹
            filePath.mkdir();
        }

        //获取原始文件名称（包括格式）
        String originalFileName = file.getOriginalFilename();

        //获取文件类型，以最后一个‘.’为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);

        //获取文件名称（不包含格式）
        String name = originalFileName.substring(0,originalFileName.lastIndexOf("."));

        //设置文件新名称：当前事件+文件名称（不包含格式）
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(d);
        String fileName = date  + "." +type;

        //在指定路径下创建文件
        File targetFile = new File(path,fileName);

        Map<String,Object> result = new HashMap<String,Object>();//定义结果
        //将文件保存到服务器指定位置
        r.setCode(200);
        try{
            file.transferTo(targetFile);
        }catch (IOException e) {
            e.printStackTrace();
            r.setCode(500);
            r.setMsg("The server is not stored correctly. Try again");//服务器未能正确存储
            return r;
        }
        //2.添加到img表中
        Imags imags = new Imags();
        imags.setName(name);
        imags.setSrc(path + "\\" +fileName);
        boolean save = imagsService.save(imags);
        if(save){
            r.setMsg("success");
            r.setDate(name);//返回前端用于作标识符,用时间表示唯一name
        }
        else{
            r.setMsg("The server is abnormal. Try again");
            r.setCode(500);
        }
        return r;
    }

    //2.提交新闻
    @PostMapping("/news/add")
    public R addNews(@RequestBody Map<String,Object> map){
        R r = new R();
        //1.获取请求数据
        News news = (News) map.get("news");//包含title，introduction
        List<Authors> authorsList = (List<Authors>) map.get("[Author]");
        //2.提交news主表
        //返回map中有code ， imgNames ， newsId
        Map<String, Object> one = newsService.addNews(news);
        int code = (int) one.get("code");
        if(code == 200){
            //news提交成功
            //3.获取与之相关的imgId
            int newsId = (int) one.get("newsId");
            List<String> imgNames = (List<String>) map.get("imgNames");
            //3.1新闻使用了图片,标识不为空
            if(imgNames.size()!=0)
            {
                Map<String, Object> two = imagsService.selectByNames(imgNames);//通过name找id
                //two包含imgIds,code
                int code1 = (int) two.get("code");
                List<Integer> imgIds = (List<Integer>) two.get("imgIds");
                //获取
                if(code1==200){
                    //4.建立img和news联系
                    int niflag = newsImagsService.addByNewsidAndImagid(newsId, imgIds);
                    if(niflag==500){//img 和 news 建立失败
                        r.setMsg("Connection img and news failed");
                        r.setCode(500);
                        return r;
                    }
                }else{//img库中图片丢失,直接返回
                    r.setMsg("Image not found");
                    r.setCode(404);
                    return r;
                }
            }
            //步骤3完成（用照片和不用照片都过这）
            //4.提交author
            Map<String, Object> three = authorsService.addAuthor(authorsList);
            //three包含authorIds,code
            int code2 = (int) three.get("code");
            if(code2==200){//author提交成功
                //5.建立author和news联系
                List<Integer> authorIds = (List<Integer>) three.get("authorIds");
                int naflag = newsAuthorService.addByNewsidAndAuthorid(newsId, authorIds);
                if(naflag != 200){//img 和 news 建立失败
                    r.setMsg("Connection author and news failed");
                    r.setCode(500);
                    return r;
                }
            }else{//author提交失败
                r.setCode(500);
                r.setMsg("Author Commit failed , try again later");
            }
            //news提交失败
        }else{
            r.setCode(500);
            r.setMsg("News Commit failed , try again later");
        }
        return r;
    }
    //3.查看新闻
    @PostMapping("/news/view")
    public R viewNews(@RequestBody News news){
        R r = new R();
        //1.通过title找news
        Map<String, Object> one = newsService.selectByTitle(news);
        //one中是news和code
        int code = (int) one.get("code");
        if(code==200){//news找到
            //2.找news和author对应关系
            News news1 = (News) one.get("news");
            List<Integer> authorIds = newsAuthorService.selectAuthorIdsByNewsId(news1.getId());
            //不会返回null，最多为空
            //3.找到了authorIds
            List<Authors> authors = authorsService.selectByIds(authorIds);
            news1.setAuthors(authors);
            r.setCode(200);
            r.setMsg("success");
            r.setDate(news1);
        }else if(code == 404){//news未找到
            r.setCode(404);
            r.setMsg("News Not Found");
            return r;
        }
        return r;
    }
    //3.删除新闻
    @PostMapping("/news/del")
    public R delNews(@RequestBody News news){
        R r = new R();
        //1.找到对应newsId
        Map<String, Object> one = newsService.selectByTitle(news);
        ////one中是news和code
        int flag1 = (int) one.get("code");
        if(flag1 == 200){//找到目标news
            News firstNews = (News) one.get("news");
            //2.找imgId
            //判断是否使用过图片
            if(newsImagsService.selectByNid(firstNews.getId())){
                List<Integer> imgIds = newsImagsService.selectImgIdsByNewId(firstNews.getId());
                //找到后删除从表中数据
                int flag2 = newsImagsService.deleteByNewsId(firstNews.getId());
                if(flag2==200){
                    //3.deleteImg
                    int flag3 = imagsService.deleteById(imgIds);
                    if(flag3!=200){//删除img失败后面是怕以后还有其他情况没考虑，方便添加的
                        if(flag3==500){
                            r.setCode(500);
                            r.setMsg("Not found");
                        }
                        return r;
                    }
                }else{//删除img-news从表数据失败
                    if(flag2==500){
                        r.setCode(500);
                        r.setMsg("Not found");
                    }
                    return r;
                }
            }
            //5.找author
            List<Integer> authorIds = newsAuthorService.selectAuthorIdsByNewsId(firstNews.getId());
            int flag4 = newsAuthorService.deleteByNewsId(firstNews.getId());
            if(flag4==200){
                //6.delAuthor
                int flag5 = authorsService.deleteById(authorIds);
                if(flag5!=200){//删除img失败
                    if(flag5==500){
                        r.setCode(500);
                        r.setMsg("Not found");
                    }
                    return r;
                }else{//完成
                    r.setCode(200);
                    r.setMsg("success");
                }
            }else{
                if(flag4==500){
                    r.setCode(500);
                    r.setMsg("Not found");
                }
                return r;
            }
        }else{//未找到里面分开判断为了之后可以出现其他情况
            if(flag1 == 500){
                r.setCode(500);
                r.setMsg("Not found");
            }
           return r;
        }
        return r;//当全部正常执行时会从这返回
    }
    //4.新闻模糊查询
    @PostMapping("/news/find")
    public R findNews(@RequestBody Map<String,Object> map){
        R r = new R();
        //获取数据
        long currentPage = Long.valueOf(map.get("currentPage").toString());
        long pageSize = Long.valueOf(map.get("pageSize").toString());
        String title = (String) map.get("title");
        News news = new News();
        news.setTitle(title);
        //模糊查询
        Map<String, Object> one = newsService.selectPage(currentPage, pageSize, news);
        //
        if(one.get("records")==null){
            r.setCode(404);
            r.setMsg("Content does not exist");
        }else{
            r.setCode(200);
            r.setMsg("success");
            r.setDate(one);
        }
        return r;
    }

    //公告操作
    //1.写公告
    @PostMapping("/inform/add")
    public R addInform(@RequestBody Inform inform){
        R r = new R();
        int i = informService.addInform(inform);
        if(i == 200){
            r.setMsg("success");
        }else if(i == 500){
            r.setMsg("The server is busy. Try again later");
        }
        r.setCode(i);
        return r;
    }

    //2.查看公告
    @PostMapping("/inform/view")
    public R viewInform(@RequestBody Inform inform){
        R r = new R();
        Inform info = informService.getById(inform.getId());
        if(info!=null){
            r.setMsg("success");
            r.setCode(200);
            r.setDate(info);
        }
        else{
            r.setCode(500);
            r.setMsg("The server is busy. Try again later");
        }
        return r;
    }

    //3.删除公告
    @PostMapping("/inform/delete")
    public R deleteInform(@RequestBody Inform inform){
        R r = new R();
        boolean b = informService.removeById(inform.getId());
        if(b){
            r.setMsg("success");
            r.setCode(200);
        }
        else{
            r.setCode(500);
            r.setMsg("The server is busy. Try again later");
        }
        return r;
    }

    //4.通过title模糊查询
    @PostMapping("/inform/find")
    public R findByTitle(@RequestBody Map<String,Object> map){
        R r = new R();
        long currentPage = Long.valueOf(map.get("currentPage").toString());
        long pageSize = Long.valueOf(map.get("pageSize").toString());
        String title = (String) map.get("title");
        Inform inform = new Inform();
        inform.setTitle(title);
        Map<String, Object> mm = informService.selectPage(currentPage, pageSize, inform);
        if(mm.get("records")==null){
            r.setCode(404);
            r.setMsg("Content does not exist");
        }else{
            r.setCode(200);
            r.setMsg("success");
            r.setDate(mm);
        }
        return r;
        /*
        map.put("totalInfoNum",totalInfoNum);
        map.put("totalPageNum",totalPageNum);
        map.put("records",records);*/
    }
    //department
    //1.查看view
    @PostMapping("/department/view")
    public R viewDepartment(@RequestBody Department department){
        R r = new R();
        //1.通过flag查看
        Map<String, Object> map = departmentService.selectByFlag(department);
        int code = (int) map.get("code");
        r.setCode(code);
        if(code==200){
            r.setDate(map.get("data"));
            r.setMsg("success");
        }else{
            if(code==500){
                r.setMsg("Not found");
            }
        }
        return r;
    }
    //修改
    @PostMapping("/department/update")
    public R updateDepartment(@RequestBody Department department){
        R r = new R();
        //1.通过flag查看
        int code = departmentService.updateInfo(department);
        r.setCode(code);
        if(code==200){
            r.setMsg("success");
        }else{
            if(code==500){
                r.setMsg("Not found");
            }
        }
        return r;
    }
    //删除
    @PostMapping("/department/del")
    public R delDepartment(@RequestBody Department department){
        R r = new R();
        //1.通过flag查看
        int code = departmentService.deleteByName(department);
        r.setCode(code);
        if(code==200){
            r.setMsg("success");
        }else{
            if(code==500){
                r.setMsg("The server is busy. Try again later");
            }
        }
        return r;
    }
    //添加
    @PostMapping("/department/add")
    public R addDepartment(@RequestBody Department department){
        R r = new R();
        //1.通过flag查看
        int code = departmentService.addDepartment(department);
        r.setCode(code);
        if(code==200){
            r.setMsg("success");
        }else{
            if(code==500){
                r.setMsg("The server is busy. Try again later");
            }
        }
        return r;
    }
}
