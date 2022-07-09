package com.msweb.msclubweb.mapper;

import com.msweb.msclubweb.domain.Recruitment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author 86189
* @description 针对表【recruitment】的数据库操作Mapper
* @createDate 2022-07-07 13:52:44
* @Entity com.msweb.msclubweb.domain.Recruitment
*/
@Mapper
@Repository
public interface RecruitmentMapper extends BaseMapper<Recruitment> {

}




