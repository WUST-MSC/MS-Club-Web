package com.msweb.msclubweb.mapper;

import com.msweb.msclubweb.domain.ImgCache;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author 86189
* @description 针对表【img_cache】的数据库操作Mapper
* @createDate 2022-07-14 21:22:01
* @Entity com.msweb.msclubweb.domain.ImgCache
*/
@Mapper
@Repository
public interface ImgCacheMapper extends BaseMapper<ImgCache> {
    @Update("TRUNCATE TABLE img_cache;")
    void deleteCache();

    @Select("select * from img_cache")
    List<ImgCache> selectAll();
}




