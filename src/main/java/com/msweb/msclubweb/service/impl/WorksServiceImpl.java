package com.msweb.msclubweb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msweb.msclubweb.domain.Honor;
import com.msweb.msclubweb.domain.Recruitment;
import com.msweb.msclubweb.domain.Works;
import com.msweb.msclubweb.mapper.WorksMapper;
import com.msweb.msclubweb.service.WorksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorksServiceImpl extends ServiceImpl<WorksMapper, Works> implements WorksService{

    @Autowired
    WorksMapper worksMapper;

    //添加项目或作品
    @Override
    public Integer addWorks(Works works){

        int flag=worksMapper.insert(works);
        if (flag > 0) return 1;
        else return 0;

    }

    //删除项目或作品
    @Override
    public Integer deleteById(Works works){
        int flag=worksMapper.deleteById(works.getId());
        if (flag > 0) return 1;
        else return 0;
    }

    //查询所有
    @Override
    public List<Works> selectAll (){
        List<Works> message=worksMapper.selectList(null);
        return message;
    }

    //根据id查询
    @Override
    public Works selectById(Integer id)
    {
        QueryWrapper<Works> one = new QueryWrapper<>();
        one.ge("id",id);
        Works message=worksMapper.selectOne(one);
        return message;
    }

    //按照flag查看项目或作品
    @Override
    public List<Works> selectByFlag (Integer flag)
    {
        QueryWrapper<Works> one = new QueryWrapper<>();
        one.ge("flag",flag);
        List<Works> message=worksMapper.selectList(one);
        return message;
    }

    @Override
    public Integer Update (Works works)
    {
        Integer flag=worksMapper.updateById(works);
        if (flag > 0) return 1;
        else return 0;
    }
}
