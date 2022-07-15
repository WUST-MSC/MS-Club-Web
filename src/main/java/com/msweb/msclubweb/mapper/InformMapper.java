package com.msweb.msclubweb.mapper;

import com.msweb.msclubweb.domain.Inform;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author 86189
* @description 针对表【inform】的数据库操作Mapper
* @createDate 2022-07-07 09:24:47
* @Entity com.msweb.msclubweb.domain.Inform
*/
@Mapper
@Repository
public interface InformMapper extends BaseMapper<Inform> {
    @Select("select *  from inform where introduction like concat('%',#{condition},'%') order by id desc limit #{pageSize} offset #{pages} ")
    /**    @Select({"<script> SELECT * FROM inform" +
            "<where>" +
            "<if test=' condition != null'> introducthon like #{}</if> " +
            "</where> " +
            "</script>"})*/
    List<Inform> selectCurrentPage(String condition,long pageSize,long pages);

}




