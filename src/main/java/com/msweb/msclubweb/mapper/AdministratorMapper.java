package com.msweb.msclubweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.msweb.msclubweb.domain.Administrator;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdministratorMapper extends BaseMapper<Administrator> {
}
