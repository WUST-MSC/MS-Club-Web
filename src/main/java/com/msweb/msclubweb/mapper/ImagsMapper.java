package com.msweb.msclubweb.mapper;

import com.msweb.msclubweb.domain.Imags;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author 86189
* @description 针对表【imags】的数据库操作Mapper
* @createDate 2022-07-07 09:26:00
* @Entity com.msweb.msclubweb.domain.Imags
*/
@Mapper
@Repository
public interface ImagsMapper extends BaseMapper<Imags> {

}




