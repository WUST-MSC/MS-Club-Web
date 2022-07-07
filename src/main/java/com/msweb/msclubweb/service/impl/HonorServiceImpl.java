package com.msweb.msclubweb.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msweb.msclubweb.domain.BackPage;
import com.msweb.msclubweb.domain.Honor;
import com.msweb.msclubweb.mapper.HonorMapper;
import com.msweb.msclubweb.service.HonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HonorServiceImpl extends ServiceImpl<HonorMapper, Honor> implements HonorService {

    @Autowired
    HonorMapper honorMapper;

    //添加荣誉
    @Override
    public Integer AddHonor(Honor honor){

        int flag=honorMapper.insert(honor);
        if (flag==1) return 200;
        else return 500;
    }

    //删除荣誉
    @Override
    public Integer deleteById(Integer id){
        int flag=honorMapper.deleteById(id);
        if(flag==1) return 200;
        else return 100;
    }

    //分页查询
    @Override
    public BackPage<Honor> selectPage (Integer pageNumber){
        //每一页展示number个数据
        Integer number=10;
        BackPage<Honor> HonorBackPage = new BackPage<>();
        Page<Honor> HonorPage=new Page<>(pageNumber,number);
        IPage<Honor> HonorIPage= page(HonorPage,null);
        //数据封装
        HonorBackPage.setContentList(HonorIPage.getRecords());
        HonorBackPage.setCurrentPage(HonorIPage.getCurrent());
        HonorBackPage.setTotalPage(HonorIPage.getPages());
        HonorBackPage.setTotalNum(HonorIPage.getTotal());
        return HonorBackPage;
    }

    //查询所有荣誉
    @Override
    public List<Honor> selectAll(){
        List<Honor> message=honorMapper.selectList(null);
        return message;
    }
}
