package com.msweb.msclubweb.mapper;

import com.msweb.msclubweb.domain.Inform;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author 86189
* @description 针对表【inform】的数据库操作Mapper
* @createDate 2022-07-07 09:24:47
* @Entity com.msweb.msclubweb.domain.Inform
*/
@Mapper
@Repository
public interface InformMapper extends BaseMapper<Inform> {

}




