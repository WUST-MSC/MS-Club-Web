package com.msweb.msclubweb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.msweb.msclubweb.domain.Administrator;
import com.msweb.msclubweb.domain.BackPage;
import com.msweb.msclubweb.domain.Honor;
import com.msweb.msclubweb.domain.Recruitment;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public interface RecruitmentService extends IService<Recruitment> {
    //添加
    public Integer AddRecruitment(Recruitment recruitment);

    //通过Email删除
    public Integer deleteByEmail(Recruitment recruitment);


    //查询全部
    public List<Recruitment> selectAll();

    //按照学号查询信息
    public Recruitment selectByEmail (Recruitment recruitment);
}
