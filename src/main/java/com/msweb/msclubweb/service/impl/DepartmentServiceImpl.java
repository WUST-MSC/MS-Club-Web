package com.msweb.msclubweb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msweb.msclubweb.domain.Department;
import com.msweb.msclubweb.service.DepartmentService;
import com.msweb.msclubweb.mapper.DepartmentMapper;
import org.springframework.stereotype.Service;

/**
* @author 86189
* @description 针对表【department】的数据库操作Service实现
* @createDate 2022-07-14 08:56:14
*/
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department>
    implements DepartmentService{
    private DepartmentMapper departmentMapper;

}




