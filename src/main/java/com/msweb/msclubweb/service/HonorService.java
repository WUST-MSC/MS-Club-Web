package com.msweb.msclubweb.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.msweb.msclubweb.domain.BackPage;
import com.msweb.msclubweb.domain.Honor;

import java.util.List;
import java.util.Map;

public interface HonorService extends IService<Honor> {
    //添加荣誉
    public Integer AddHonor(Honor honor);

    //删除荣誉
    public Integer deleteById(Integer id);

    //分页功能
    public BackPage<Honor> selectPage (Integer page);

    //查询数据
    public List<Honor> selectAll();
}
