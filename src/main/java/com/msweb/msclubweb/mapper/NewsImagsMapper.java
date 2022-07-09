package com.msweb.msclubweb.mapper;

import com.msweb.msclubweb.domain.NewsImags;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author 86189
* @description 针对表【news_imags】的数据库操作Mapper
* @createDate 2022-07-07 09:25:47
* @Entity com.msweb.msclubweb.domain.NewsImags
*/
@Mapper
@Repository
public interface NewsImagsMapper extends BaseMapper<NewsImags> {

}




