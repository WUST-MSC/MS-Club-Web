package com.msweb.msclubweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.msweb.msclubweb.domain.Works;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface WorksMapper extends BaseMapper<Works> {
}
