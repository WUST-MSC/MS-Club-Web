package com.msweb.msclubweb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msweb.msclubweb.domain.NewsAuthor;
import com.msweb.msclubweb.service.NewsAuthorService;
import com.msweb.msclubweb.mapper.NewsAuthorMapper;
import org.springframework.stereotype.Service;

/**
* @author 86189
* @description 针对表【news_author】的数据库操作Service实现
* @createDate 2022-07-07 09:25:42
*/
@Service
public class NewsAuthorServiceImpl extends ServiceImpl<NewsAuthorMapper, NewsAuthor>
    implements NewsAuthorService{

}




