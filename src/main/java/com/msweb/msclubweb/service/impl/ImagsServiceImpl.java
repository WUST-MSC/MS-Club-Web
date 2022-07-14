package com.msweb.msclubweb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msweb.msclubweb.controller.Result;
import com.msweb.msclubweb.domain.Imags;
import com.msweb.msclubweb.mapper.ImagsMapper;
import com.msweb.msclubweb.service.ImagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ImagsServiceImpl extends ServiceImpl<ImagsMapper, Imags> implements ImagsService {
    @Autowired
    private ImagsMapper imagsMapper;
    @Value("${file.upload.path}")
    private String path;

    @Override
    public Result addImg(MultipartFile file) {
        //保存图片
        File filePath = new File(path);
        if(!filePath.exists() && !filePath.isDirectory()){
            boolean flag=filePath.mkdir();
            if(!flag) return new Result(500,"图片保存失败(创建目录失败)");
        }
        String originalFileName = file.getOriginalFilename();
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(d);
        String fileName = date  + "." +type;
        File targetFile = new File(path,fileName);
        //将文件保存到服务器指定位置
        try{
            file.transferTo(targetFile);
        }catch (IOException e) {
            e.printStackTrace();
            return new Result(500,"error");
        }
        //存入数据库
        Imags imags = new Imags();
        imags.setSrc(path+"/"+fileName);
        imags.setName(date);//以时间戳为名字
        int insert = imagsMapper.insert(imags);
        return insert==0?new Result(500,"error") :new Result(200,"success");
    }
}
