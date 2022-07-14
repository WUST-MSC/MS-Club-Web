package com.msweb.msclubweb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.msweb.msclubweb.domain.BackPage;
import com.msweb.msclubweb.domain.Honor;
import com.msweb.msclubweb.domain.PageSelect;

import java.util.List;

public interface HonorService extends IService<Honor> {
    //添加荣誉
    public Integer AddHonor(Honor honor);

    //删除荣誉
    public Integer deleteByTitle(Honor honor);

    //更改
    public Integer Update (Honor honor);

    //分页分类别查询
    public BackPage<Honor> selectPage (PageSelect pageSelect);

    //查询数据
    public List<Honor> selectAll();

    //按照Title查询
    public Honor selectByTitle(Honor honor);


}
