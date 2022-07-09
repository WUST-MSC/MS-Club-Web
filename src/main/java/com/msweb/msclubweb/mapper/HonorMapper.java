package com.msweb.msclubweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.msweb.msclubweb.domain.Honor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface HonorMapper extends BaseMapper<Honor> {
}
