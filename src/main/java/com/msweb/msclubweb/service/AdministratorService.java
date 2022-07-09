package com.msweb.msclubweb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.msweb.msclubweb.domain.Administrator;

import java.util.List;

public interface AdministratorService extends IService<Administrator> {
    //添加
    public Integer AddAdministrator(Administrator administrator);

    //删除
    public Integer deleteById( Administrator administrator);

    //更改
    public Integer Update (Administrator administrator);

    //查询数据
    public List<Administrator> selectAll();
}
