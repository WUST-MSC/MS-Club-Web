package com.msweb.msclubweb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.msweb.msclubweb.domain.Works;

import java.util.List;

public interface WorksService extends IService<Works> {

    //添加项目或作品
    public Integer addWorks(Works works);

    //删除项目或作品
    public Integer deleteByTitle(Works works);

    //更改
    public Integer Update (Works works);

    //查询全部
    public List<Works> selectAll();

    //按照Title查询
    public Works selectByTitle(Works works);

    //按照flag查看项目或作品
    public List<Works> selectByFlag (Integer flag);

}
