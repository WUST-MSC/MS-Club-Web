package com.msweb.msclubweb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msweb.msclubweb.domain.Department;
import com.msweb.msclubweb.service.DepartmentService;
import com.msweb.msclubweb.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    public int addDepartment(Department department) {
       /* //判断不为空
        if(department.getQq()==null || department.getQq().length()==0 ||
                department.getEmail()==null || department.getEmail().length()==0 ||
                department.getName()==null || department.getName().length()==0 )
            return 400;
        //检验数据是否合法
        //1.qq不能包含非数字
        String qq = department.getQq();
        char[] chars = qq.toCharArray();
        for(int i =0;i<chars.length;i++){
            if (chars[i]>'9'&&chars[i]<'0') return 400;
        }*/
        //添加
        int insert = departmentMapper.insert(department);
        if(insert==1) return 200;
        else return 500;
    }

    @Override
    public int deleteByName(String name) {
        LambdaQueryWrapper<Department> one = new LambdaQueryWrapper<>();
        one.eq(name!=null&&name.length()!=0,
                Department::getName,
                name);
        int delete = departmentMapper.delete(one);
        if(delete == 1)return 200;
        else return 500;
    }

    @Override
    public int updateInfo(Department department) {
        departmentMapper.updateById(department);
        return 0;
    }

    @Override
    public Map<String,Object> selectByFlag(int flag) {
        HashMap<String, Object> map = new HashMap<>();
        //查找
        LambdaQueryWrapper<Department> one = new LambdaQueryWrapper<>();
        one.eq(Department::getFlag,flag);
        Department result = departmentMapper.selectOne(one);
        //判断
        if(result != null){
            map.put("flag",200);
            map.put("data",result);
        }else map.put("flag",500);
        return map;
    }
}




