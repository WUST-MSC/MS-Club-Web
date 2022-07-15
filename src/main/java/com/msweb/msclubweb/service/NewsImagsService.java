package com.msweb.msclubweb.service;

import com.msweb.msclubweb.domain.NewsImags;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author 86189
* @description 针对表【news_imags】的数据库操作Service
* @createDate 2022-07-07 09:25:47
*/
public interface NewsImagsService extends IService<NewsImags> {
    //添加
    int addByNewsidAndImagid(int newsid, List<Integer> imgIds);
    //判断是否使用过图片
    boolean selectByNid(int newsid);
    //判断图片是否被使用过
    boolean selectByImgId(int imgId);
    //通过newsid找
    List<NewsImags> selectByNewsId(int newsId);
    //通过newsid直接获取imgIds为方便
    List<Integer> selectImgIdsByNewId(int newsId);
    //通过newsid删除
     boolean deleteByNewsId(int newsId);
}
