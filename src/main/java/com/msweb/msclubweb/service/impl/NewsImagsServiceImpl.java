package com.msweb.msclubweb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msweb.msclubweb.domain.NewsImags;
import com.msweb.msclubweb.service.NewsImagsService;
import com.msweb.msclubweb.mapper.NewsImagsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* @author 86189
* @description 针对表【news_imags】的数据库操作Service实现
* @createDate 2022-07-07 09:25:47
*/
@Service
public class NewsImagsServiceImpl extends ServiceImpl<NewsImagsMapper, NewsImags>
    implements NewsImagsService{
    @Autowired
    private NewsImagsMapper newsImagsMapper;

    @Override
    public int addByNewsidAndImagid(int newsid, List<Integer> imgIds) {
        for(int i = 0;i<imgIds.size();i++){
            NewsImags newsImags = new NewsImags();
            newsImags.setNewsId(newsid);
            newsImags.setImagId(imgIds.get(i));
            //————————————可能要添加事务回滚——————————————————
            int insert = newsImagsMapper.insert(newsImags);
            if(insert==0) return 500;
        }
        return 200;
    }

    @Override
    public boolean selectByNid(int newsid) {
        LambdaQueryWrapper<NewsImags> one = new LambdaQueryWrapper<>();
        one.eq(NewsImags::getNewsId, newsid);
        List<NewsImags> newsImags = newsImagsMapper.selectList(one);
        if(newsImags.size()>0){
            return true;//用过图片
        }else{
            return false;//没用过图片
        }
    }

    //图片是否被使用
    @Override
    public boolean selectByImgId(int imgId) {
        LambdaQueryWrapper<NewsImags> one = new LambdaQueryWrapper<>();
        one.eq(NewsImags::getImagId,imgId);
        List<NewsImags> newsImags = newsImagsMapper.selectList(one);
        return newsImags.size()>0?true:false;
    }

    //通过newsid找到对应imagid
    @Override
    public List<NewsImags> selectByNewsId(int newsId) {
        LambdaQueryWrapper<NewsImags> one = new LambdaQueryWrapper<>();
        one.eq(NewsImags::getNewsId, newsId);

        List<NewsImags> newsImags = newsImagsMapper.selectList(one);
        return newsImags;
    }

    @Override
    public List<Integer> selectImgIdsByNewId(int newsId) {
        LambdaQueryWrapper<NewsImags> one = new LambdaQueryWrapper<>();
        one.eq(NewsImags::getNewsId, newsId);

        ArrayList<Integer> list = new ArrayList<>();
        List<NewsImags> newsImags = newsImagsMapper.selectList(one);
        for(int i = 0;i<newsImags.size();i++){
            list.add(newsImags.get(i).getImagId());
        }
        return list;
    }

    //用于在删除新闻时删除对应图片（在判断完）
    @Override
    public boolean deleteByNewsId(int newsId) {
        LambdaQueryWrapper<NewsImags> one = new LambdaQueryWrapper<>();
        one.eq(NewsImags::getNewsId, newsId);

        int delete = newsImagsMapper.delete(one);
        if(delete>0) return true;
        else return false;
    }
}




