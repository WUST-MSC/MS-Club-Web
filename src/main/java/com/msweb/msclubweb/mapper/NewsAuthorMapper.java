package com.msweb.msclubweb.mapper;

import com.msweb.msclubweb.domain.NewsAuthor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author 86189
* @description 针对表【news_author】的数据库操作Mapper
* @createDate 2022-07-07 09:25:42
* @Entity com.msweb.msclubweb.domain.NewsAuthor
*/
@Mapper
@Repository
public interface NewsAuthorMapper extends BaseMapper<NewsAuthor> {

}




