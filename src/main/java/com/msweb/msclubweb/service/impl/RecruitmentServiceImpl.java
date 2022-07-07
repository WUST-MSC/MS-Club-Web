package com.msweb.msclubweb.service.impl;

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

import java.util.List;

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
    public List<Recruitment> message(){
         List<Recruitment> message=recruitmentMapper.selectList(null);
         return message;
    }

    //分页查询信息
    @Override
    public BackPage<Recruitment> selectPage (Integer pageNumber){
        Integer number=10;
        BackPage<Recruitment> RecruitmentBackPage = new BackPage<>();
        Page<Recruitment> RecruitmentPage=new Page<>(pageNumber,number);
        IPage<Recruitment> RecruitmentIPage= page(RecruitmentPage,null);
        //数据封装
        RecruitmentBackPage.setContentList(RecruitmentIPage.getRecords());
        RecruitmentBackPage.setCurrentPage(RecruitmentIPage.getCurrent());
        RecruitmentBackPage.setTotalPage(RecruitmentIPage.getPages());
        RecruitmentBackPage.setTotalNum(RecruitmentIPage.getTotal());
        return RecruitmentBackPage;
    }
}