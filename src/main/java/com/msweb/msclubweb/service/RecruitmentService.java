package com.msweb.msclubweb.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.IService;
import com.msweb.msclubweb.domain.BackPage;
import com.msweb.msclubweb.domain.PageSelect;
import com.msweb.msclubweb.domain.Recruitment;

import java.util.List;

public interface RecruitmentService extends IService<Recruitment> {
    //添加
    public Integer AddRecruitment(Recruitment recruitment);

    //通过Email删除
    public Integer deleteByEmail(Recruitment recruitment);

    //通过学号删除
    public Integer deleteByS_Id(Recruitment recruitment);

    //查询全部
    public List<Recruitment> selectAll();

    //按照学号查询信息
    public Recruitment selectByS_Id (Recruitment recruitment);

    //搜索(姓名，学号12位)结果分页
    public BackPage<Recruitment> selectByKey(PageSelect pageSelect , String key);

    //重复判定
    public Integer SearchSame(Recruitment recruitment);

    public Integer deleteById(int id);

}
