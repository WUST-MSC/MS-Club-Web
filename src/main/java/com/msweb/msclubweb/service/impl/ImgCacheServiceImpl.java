package com.msweb.msclubweb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msweb.msclubweb.domain.ImgCache;
import com.msweb.msclubweb.service.ImgCacheService;
import com.msweb.msclubweb.mapper.ImgCacheMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 86189
* @description 针对表【img_cache】的数据库操作Service实现
* @createDate 2022-07-14 21:22:01
*/
@Service
public class ImgCacheServiceImpl extends ServiceImpl<ImgCacheMapper, ImgCache>
    implements ImgCacheService{
    @Autowired
    private ImgCacheMapper imgCacheMapper;

    @Override
    public void addCache(ImgCache imgCache) {
        imgCacheMapper.insert(imgCache);
    }

    @Override
    public void deleteCache() {
        imgCacheMapper.deleteCache();
    }

    @Override
    public List<ImgCache> getImgCache() {
        return imgCacheMapper.selectAll();
    }
}




