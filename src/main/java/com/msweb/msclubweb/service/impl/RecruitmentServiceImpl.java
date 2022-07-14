package com.msweb.msclubweb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msweb.msclubweb.domain.BackPage;
import com.msweb.msclubweb.domain.Honor;
import com.msweb.msclubweb.domain.PageSelect;
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
    public Integer deleteByEmail(Recruitment recruitment) {
        LambdaQueryWrapper<Recruitment> one = new LambdaQueryWrapper<>();
        one.eq(!recruitment.getEmail().isEmpty(), Recruitment::getEmail, recruitment.getEmail());
        int flag = recruitmentMapper.delete(one);
        if (flag > 0) return 1;
        else return 0;
    }

    //查询所有信息
    @Override
    public List<Recruitment> selectAll() {
        return recruitmentMapper.selectList(null);
    }

    //按照学号查询信息
    @Override
    public Recruitment selectByS_Id(Recruitment recruitment) {
        LambdaQueryWrapper<Recruitment> one = new LambdaQueryWrapper<>();
        one.eq(recruitment.getStudent_id() != null, Recruitment::getStudent_id, recruitment.getStudent_id());
        return recruitmentMapper.selectOne(one);
    }

    //根据姓名、学号分页查询
    @Override
    public BackPage<Recruitment> selectByKey(PageSelect pageSelect, String key) {
        int number = 10;
        BackPage<Recruitment> RecruitmentBackPage = new BackPage<>();
        QueryWrapper<Recruitment> one = new QueryWrapper<>();
        one.like(key.length() != 0, "name", key).or().like(key.length() != 0, "student_id", key);

        Page<Recruitment> RecruitmentPage = new Page<>(pageSelect.getPageNumber(), number);
        IPage<Recruitment> HonorIPage = page(RecruitmentPage, one);
        //数据封装
        RecruitmentBackPage.setContentList(HonorIPage.getRecords());
        RecruitmentBackPage.setCurrentPage(HonorIPage.getCurrent());
        RecruitmentBackPage.setTotalPage(HonorIPage.getPages());
        RecruitmentBackPage.setTotalNum(HonorIPage.getTotal());
        return RecruitmentBackPage;
    }

    ;

    //重复判定
    @Override
    public Integer SearchSame(Recruitment recruitment) {
        LambdaQueryWrapper<Recruitment> one = new LambdaQueryWrapper<>();
        one.eq(!recruitment.getStudent_id().isEmpty(), Recruitment::getStudent_id, recruitment.getStudent_id());
        Integer count = recruitmentMapper.selectCount(one);
        //有相同的返回1
        return count > 0 ? 1 : 0;
    }

    //根据id删除
    @Override
    public Integer deleteById(int id) {
        return recruitmentMapper.deleteById(id);
    }

    @Override
    public Integer deleteByS_Id(Recruitment recruitment) {
        LambdaQueryWrapper<Recruitment> one = new LambdaQueryWrapper<>();
        one.eq(!recruitment.getStudent_id().isEmpty(), Recruitment::getStudent_id, recruitment.getEmail());
        return recruitmentMapper.delete(one);
    }


}