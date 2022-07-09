package com.msweb.msclubweb.service;

import com.msweb.msclubweb.domain.Imags;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author 86189
* @description 针对表【imags】的数据库操作Service
* @createDate 2022-07-07 09:26:00
*/
public interface ImagsService extends IService<Imags> {
    //添加图片
    int addImg(Imags imags);
    //通过name找id
    Imags selectByName(String imgName);
    //通过names找id
    Map<String,Object> selectByNames(List<String> imgNames);
    //deleteById----removeById,用于删除new时连同删除照片
    //新要求，通过ids批量删除
    int deleteById(List<Integer> imgIds);
}
