package com.msweb.msclubweb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.msweb.msclubweb.domain.Works;

import java.util.List;

public interface WorksService extends IService<Works> {

    public Integer addWorks(Works works);

    public Integer deleteById(Integer id);

    public List<Works> selectAll();
}
