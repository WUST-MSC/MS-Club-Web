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
    //1.添加，写着以后可以添加队内容的合理性判断
    int addDepartment(Department department);
    //2.删除通过name（换届）应该不会有两届同名同姓吧awa
    int deleteByName(String name);
    //3.修改（部门管理信息更改）写着以后可以添加队内容的合理性判断
    int updateInfo(Department department);
    //4.查找（通过flag表示各个部门）
    Map<String,Object> selectByFlag(int flag);
}
