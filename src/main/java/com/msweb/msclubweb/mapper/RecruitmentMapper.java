package com.msweb.msclubweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.msweb.msclubweb.domain.Recruitment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RecruitmentMapper extends BaseMapper<Recruitment> {
}
