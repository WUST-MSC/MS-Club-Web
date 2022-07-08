package com.msweb.msclubweb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msweb.msclubweb.domain.BackPage;
import com.msweb.msclubweb.domain.Honor;
import com.msweb.msclubweb.domain.Recruitment;
import com.msweb.msclubweb.mapper.RecruitmentMapper;
import com.msweb.msclubweb.service.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecruitmentServiceImpl extends ServiceImpl<RecruitmentMapper, Recruitment> implements RecruitmentService {

    @Autowired
    RecruitmentMapper recruitmentMapper;

    //添加用户信息
    @Override
    public Integer AddRecruitment(Recruitment recruitment) {

        int flag = recruitmentMapper.insert(recruitment);
        if (flag == 1) return 200;
        else return 500;
    }

    //删除信息
    @Override
    public Integer deleteBtId(Integer id){
        int flag=recruitmentMapper.deleteById(id);
        if (flag == 1) return 200;
        else return 500;
    }

    //查询所有信息
    @Override
    public List<Recruitment> selectAll(){
         List<Recruitment> message=recruitmentMapper.selectList(null);
         return message;
    }

    //按照学号查询信息
    @Override
    public Recruitment selectByStudent_id (Integer student_id) {
        QueryWrapper<Recruitment> one = new QueryWrapper<>();
        one.ge("student_id",student_id);
        Recruitment message=recruitmentMapper.selectOne(one);
        return message;
    }
}