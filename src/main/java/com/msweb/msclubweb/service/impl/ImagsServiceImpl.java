package com.msweb.msclubweb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msweb.msclubweb.common.Result;
import com.msweb.msclubweb.domain.Imags;
import com.msweb.msclubweb.domain.Inform;
import com.msweb.msclubweb.service.ImagsService;
import com.msweb.msclubweb.mapper.ImagsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
* @author 86189
* @description 针对表【imags】的数据库操作Service实现
* @createDate 2022-07-07 09:26:00
*/
@Service
public class ImagsServiceImpl extends ServiceImpl<ImagsMapper, Imags>
    implements ImagsService{
    @Autowired
    private ImagsMapper imagsMapper;
    @Value("${file.upload.path}")
    private String path;

    //添加img
    @Override
    public Result<Imags> addImg(MultipartFile file) {
        //保存图片
        File filePath = new File(path);
        if(!filePath.exists() && !filePath.isDirectory()){
            filePath.mkdir();
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
            return Result.sqlError();
        }
        //存入数据库
        Imags imags = new Imags();
        imags.setSrc(path+"/"+fileName);
        imags.setName(date);//以时间戳为名字
        int insert = imagsMapper.insert(imags);
        return insert==0?Result.sqlError():Result.success(null);
    }

    @Override
    public Imags selectByName(String imgName) {
        LambdaQueryWrapper<Imags> one = new LambdaQueryWrapper<>();
        one.eq(Imags::getName,imgName);

        Imags imags = imagsMapper.selectOne(one);
        return imags;
    }

    @Override
    public Map<String, Object> selectByNames(List<String> imgNames) {
        HashMap<String, Object> map = new HashMap<>();
        ArrayList<Integer> imgIds = new ArrayList<>();

        for(int i = 0;i<imgNames.size();i++){
            //找到对应imgId
            LambdaQueryWrapper<Imags> mid = new LambdaQueryWrapper<>();
            mid.eq(Imags::getName,imgNames.get(i));

            Imags imgs1 = imagsMapper.selectOne(mid);
            if(imgs1!=null){
                imgIds.add(imgs1.getId());
            }else{
                //未找到对应图片
                map.put("code",404);
                return map;
            }
        }
        //找完后封装返回
        map.put("imgIds",imgIds);
        map.put("code",200);
        return map;
    }

    @Override
    public int deleteById(List<Integer> imgIds) {
        for(int i = 0;i<imgIds.size();i++){
            int flag = imagsMapper.deleteById(imgIds.get(i));
            if(flag!=1) return 500;
        }
        return 200;
    }

}




