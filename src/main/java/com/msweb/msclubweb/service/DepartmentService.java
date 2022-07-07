package com.msweb.msclubweb.service;

import com.msweb.msclubweb.domain.Department;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author 86189
* @description 针对表【department】的数据库操作Service
* @createDate 2022-07-07 09:24:37
*/
//部门管理
public interface DepartmentService extends IService<Department> {
    //添加
    int addDepartment(Department department);
    //删除通过name（换届）
    int deleteByName(String name);
    //修改（部门管理信息更改）
    int updateInfo(Department department);
    //查找（通过flag）
    Map<String,Object> selectByFlag(int flag);
}
