package com.msweb.msclubweb.mapper;

import com.msweb.msclubweb.domain.Authors;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author 86189
* @description 针对表【authors】的数据库操作Mapper
* @createDate 2022-07-07 09:25:53
* @Entity com.msweb.msclubweb.domain.Authors
*/
@Mapper
@Repository
public interface AuthorsMapper extends BaseMapper<Authors> {

}




