package com.msweb.msclubweb.service;

import com.msweb.msclubweb.domain.ImgCache;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 86189
* @description 针对表【img_cache】的数据库操作Service
* @createDate 2022-07-14 21:22:01
*/
public interface ImgCacheService extends IService<ImgCache> {
    //添加缓存
    void addCache(ImgCache imgCache);
    //删除缓存
    void deleteCache();
    //取出缓存
    List<ImgCache> getImgCache();
}
