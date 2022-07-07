package com.msweb.msclubweb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msweb.msclubweb.domain.News;
import com.msweb.msclubweb.service.NewsService;
import com.msweb.msclubweb.mapper.NewsMapper;
import org.springframework.stereotype.Service;

/**
* @author 86189
* @description 针对表【news】的数据库操作Service实现
* @createDate 2022-07-07 09:26:05
*/
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News>
    implements NewsService{

}




