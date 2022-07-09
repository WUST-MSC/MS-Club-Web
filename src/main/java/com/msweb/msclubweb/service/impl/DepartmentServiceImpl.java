package com.msweb.msclubweb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msweb.msclubweb.common.Result;
import com.msweb.msclubweb.domain.Department;
import com.msweb.msclubweb.service.DepartmentService;
import com.msweb.msclubweb.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 86189
* @description 针对表【department】的数据库操作Service实现
* @createDate 2022-07-07 09:24:37
*/
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department>
    implements DepartmentService{
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public Result<Department> addDepartment(Department department) {
        //1.name不能重(应该不会有两届部长同名吧)
        LambdaQueryWrapper<Department> one = new LambdaQueryWrapper<>();
        one.eq(Department::getName,department.getName());

        List<Department> list = departmentMapper.selectList(one);
        if(list.size()>0) return Result.dataError();
        //2.添加本地
        int insert = departmentMapper.insert(department);
        return insert==0?Result.sqlError():Result.success(null);
    }

    //或者通过名字（qq，email可能会更改，没办法）
    @Override
    public Result<Department> deleteByName(Department department) {
        LambdaQueryWrapper<Department> one = new LambdaQueryWrapper<>();
        one.eq(department.getName()!=null&&department.getName().length()!=0,
                Department::getName,
                department.getName());
        int delete = departmentMapper.delete(one);
        return delete==0?Result.sqlError():Result.success(null);
    }

    //修改部门管理信息,
    @Override
    public Result<Department> updateInfo(Department department) {
        //修改
        LambdaQueryWrapper<Department> one = new LambdaQueryWrapper<>();
        one.eq(Department::getName,department.getName());

        int update = departmentMapper.update(department,one);
        return update==0?Result.sqlError():Result.success(null);
    }

    //通过flag找到对应部门的管理
    @Override
    public Result<Department> findByFlag(Department department) {
        //查找
        LambdaQueryWrapper<Department> one = new LambdaQueryWrapper<>();
        one.eq(Department::getFlag,department.getFlag());
        Department result = departmentMapper.selectOne(one);
        //判断
        return result==null?Result.notFound():Result.success(result);
    }
}




