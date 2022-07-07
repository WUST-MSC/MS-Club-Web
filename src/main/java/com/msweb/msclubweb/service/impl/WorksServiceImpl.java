package com.msweb.msclubweb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
        if (flag == 1) return 200;
        else return 500;

    }

    //删除项目或作品
    @Override
    public Integer deleteById(Integer id){
        int flag=worksMapper.deleteById(id);
        if(flag==1) return 200;
        else return 500;
    }

    //查询所有
    @Override
    public List<Works> selectAll (){
        List<Works> message=worksMapper.selectList(null);
        return message;
    }


}
