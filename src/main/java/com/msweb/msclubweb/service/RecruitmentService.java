package com.msweb.msclubweb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.msweb.msclubweb.domain.BackPage;
import com.msweb.msclubweb.domain.Honor;
import com.msweb.msclubweb.domain.Recruitment;

import javax.swing.*;
import java.util.List;

public interface RecruitmentService extends IService<Recruitment> {
    //添加
    public Integer AddRecruitment(Recruitment recruitment);

    //删除
    public Integer deleteBtId(Integer id);

    //查询全部
    public List<Recruitment> message();

    //分页
    public BackPage<Recruitment> selectPage (Integer pageNumber);
}
