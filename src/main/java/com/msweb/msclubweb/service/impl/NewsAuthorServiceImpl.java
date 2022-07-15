package com.msweb.msclubweb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msweb.msclubweb.domain.Authors;
import com.msweb.msclubweb.domain.NewsAuthor;
import com.msweb.msclubweb.domain.NewsImags;
import com.msweb.msclubweb.service.NewsAuthorService;
import com.msweb.msclubweb.mapper.NewsAuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author 86189
* @description 针对表【news_author】的数据库操作Service实现
* @createDate 2022-07-07 09:25:42
*/
@Service
public class NewsAuthorServiceImpl extends ServiceImpl<NewsAuthorMapper, NewsAuthor>
    implements NewsAuthorService{
    @Autowired
    private NewsAuthorMapper newsAuthorMapper;

    //添加
    @Override
    public int addByNewsidAndAuthorid(int newsid, List<Integer> authorIds) {
        for(int i = 0;i<authorIds.size();i++){
            NewsAuthor newsAuthor = new NewsAuthor();
            newsAuthor.setNewsId(newsid);
            newsAuthor.setAuthorId(authorIds.get(i));
            //————————————可能要添加事务回滚——————————————————
            int insert = newsAuthorMapper.insert(newsAuthor);
            if(insert!=1) return 500;
        }
        return 200;
    }

    @Override
    public List<NewsAuthor> selectByNewsId(int newsId) {
        LambdaQueryWrapper<NewsAuthor> one = new LambdaQueryWrapper<>();
        one.eq(NewsAuthor::getNewsId, newsId);

        List<NewsAuthor> newsAuthors = newsAuthorMapper.selectList(one);
        return newsAuthors;
    }

    @Override
    public List<Integer> selectAuthorIdsByNewsId(int newsId) {
        LambdaQueryWrapper<NewsAuthor> one = new LambdaQueryWrapper<>();
        one.eq(NewsAuthor::getNewsId, newsId);
        //不会返回null
        ArrayList<Integer> list = new ArrayList<>();
        List<NewsAuthor> newsAuthors = newsAuthorMapper.selectList(one);
        for(int i = 0;i<newsAuthors.size();i++){
            list.add(newsAuthors.get(i).getAuthorId());
        }
        return list;
    }

    @Override
    public boolean deleteByNewsId(int newsId) {
        LambdaQueryWrapper<NewsAuthor> one = new LambdaQueryWrapper<>();
        one.eq(NewsAuthor::getNewsId, newsId);

        int delete = newsAuthorMapper.delete(one);
        if(delete>0) return true;
        else return false;
    }
}




