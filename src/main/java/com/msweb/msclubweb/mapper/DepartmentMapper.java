package com.msweb.msclubweb.mapper;

import com.msweb.msclubweb.domain.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author 86189
* @description 针对表【department】的数据库操作Mapper
* @createDate 2022-07-07 09:24:37
* @Entity com.msweb.msclubweb.domain.Department
*/
@Mapper
@Repository
public interface DepartmentMapper extends BaseMapper<Department> {

}




