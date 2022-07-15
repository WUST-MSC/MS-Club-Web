package com.msweb.msclubweb.mapper;

import com.msweb.msclubweb.domain.Inform;
import com.msweb.msclubweb.domain.News;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Indexed;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
* @author 86189
* @description 针对表【news】的数据库操作Mapper
* @createDate 2022-07-07 09:26:05
* @Entity com.msweb.msclubweb.domain.News
*/
@Mapper
@Repository
public interface NewsMapper extends BaseMapper<News> {
    News selectById(@Param("id")Integer id);
    //查询
    List<News> selectCurrentPage(String condition, long pageSize, long pages);
}




