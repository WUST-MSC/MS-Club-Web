package com.msweb.msclubweb.service;

import com.msweb.msclubweb.common.Result;
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
    //1.添加，写着以后可以添加队内容的合理性判断
    Result<Department> addDepartment(Department department);
    //2.删除通过name（换届）应该不会有两届同名同姓吧awa
    Result<Department> deleteByName(Department department);
    //3.修改（部门管理信息更改）写着以后可以添加队内容的合理性判断
    Result<Department>  updateInfo(Department department);
    //4.查找（通过flag表示各个部门）
    Result<Department>  findByFlag(Department department);
}
