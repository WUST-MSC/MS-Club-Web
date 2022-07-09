package com.msweb.msclubweb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msweb.msclubweb.domain.Honor;
import com.msweb.msclubweb.domain.Recruitment;
import com.msweb.msclubweb.mapper.RecruitmentMapper;
import com.msweb.msclubweb.service.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruitmentServiceImpl extends ServiceImpl<RecruitmentMapper, Recruitment> implements RecruitmentService {

    @Autowired
    RecruitmentMapper recruitmentMapper;

    //添加用户信息
    @Override
    public Integer AddRecruitment(Recruitment recruitment) {

        int flag = recruitmentMapper.insert(recruitment);
        if (flag > 0) return 1;
        else return 0;
    }

    //删除信息
    @Override
    public Integer deleteByEmail(Recruitment recruitment){
        LambdaQueryWrapper<Recruitment> one = new LambdaQueryWrapper<>();
        one.eq(recruitment.getEmail()!=null, Recruitment::getEmail, recruitment.getEmail());
        int flag = recruitmentMapper.delete(one);
        if(flag>0) return 1;
        else return 0;
    }

    //查询所有信息
    @Override
    public List<Recruitment> selectAll(){
        return recruitmentMapper.selectList(null);
    }

    //按照学号查询信息
    @Override
    public Recruitment selectByEmail (Recruitment recruitment) {
        LambdaQueryWrapper<Recruitment> one = new LambdaQueryWrapper<>();
        one.eq(recruitment.getEmail()!=null, Recruitment::getEmail, recruitment.getEmail());
        return recruitmentMapper.selectOne(one);
    }

}