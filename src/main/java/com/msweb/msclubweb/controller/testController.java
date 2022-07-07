package com.msweb.msclubweb.controller;

import com.msweb.msclubweb.controller.util.R;
import com.msweb.msclubweb.domain.Imags;
import com.msweb.msclubweb.domain.Inform;
import com.msweb.msclubweb.service.AuthorsService;
import com.msweb.msclubweb.service.DepartmentService;
import com.msweb.msclubweb.service.ImagsService;
import com.msweb.msclubweb.service.InformService;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    //图片上传路径
    @Value("${file.upload.path}")
    private String path;

    //1.上传图片(测试中)
    @PostMapping("/test")
    public R test(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        R r = new R();
        //1.保存图片
        //获取文件在服务器的储存位置
        File filePath = new File(path);
        System.out.println("文件的保存路径"+path);
        if(!filePath.exists() && !filePath.isDirectory()){
            System.out.println("目录不存在，创建目录" + filePath);
            filePath.mkdir();
        }

        //获取原始文件名称（包括格式）
        String originalFileName = file.getOriginalFilename();
        System.out.println("原始文件名称" + originalFileName);

        //获取文件类型，以最后一个‘.’为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        System.out.println("文件类型" + type);

        //获取文件名称（不包含格式）
        String name = originalFileName.substring(0,originalFileName.lastIndexOf("."));

        //设置文件新名称：当前事件+文件名称（不包含格式）
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(d);
        String fileName = date  + "." +type;
        System.out.println("新文件名称" +fileName);

        //在指定路径下创建文件
        File targetFile = new File(path,fileName);

        Map<String,Object> result = new HashMap<String,Object>();//定义结果
        //将文件保存到服务器指定位置
        try{
            file.transferTo(targetFile);
        }catch (IOException e) {
            e.printStackTrace();
            r.setCode(500);
        }
        //2.添加到img表中
        Imags imags = new Imags();
        imags.setName(name);
        imags.setSrc(path + "\\"+fileName);
        int i = imagsService.addImg(imags);
        if(i==200){
            r.setMsg("success");
        }
        else if(i == 500){
            r.setMsg("The server is abnormal. Try again");
        }
        r.setCode(i);
        return r;
    }

    //公告操作
    //1.写公告
    @PostMapping("/addInform")
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
    @PostMapping("/viewInform")
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
    @PostMapping("/deleteInform")
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
    @PostMapping("/findByTitle")
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
}
