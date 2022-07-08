package com.msweb.msclubweb.service;

import com.msweb.msclubweb.domain.NewsAuthor;
import com.baomidou.mybatisplus.extension.service.IService;
import com.msweb.msclubweb.domain.NewsImags;

import java.util.List;

/**
* @author 86189
* @description 针对表【news_author】的数据库操作Service
* @createDate 2022-07-07 09:25:42
*/
public interface NewsAuthorService extends IService<NewsAuthor> {
    //添加
    int addByNewsidAndAuthorid(int newsid,List<Integer> authorIds);
    //通过newsid找
    List<NewsAuthor> selectByNewsId(int newsId);
    List<Integer> selectAuthorIdsByNewsId(int newsId);
    //通过newsid删除
    int deleteByNewsId(int newsId);
}
