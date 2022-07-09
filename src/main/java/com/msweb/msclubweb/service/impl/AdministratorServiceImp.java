package com.msweb.msclubweb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msweb.msclubweb.domain.Administrator;
import com.msweb.msclubweb.mapper.AdministratorMapper;
import com.msweb.msclubweb.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorServiceImp extends ServiceImpl<AdministratorMapper, Administrator> implements AdministratorService {
    @Autowired
    private AdministratorMapper administratorMapper;

    //添加社团高层人员
    @Override
    public Integer AddAdministrator(Administrator administrator){
;
        int flag=administratorMapper.insert(administrator);
        if (flag > 0) return 1;
        else return 0;
    }

    //删除社团高层人员
    @Override
    public Integer deleteById( Administrator administrator){
        int flag=administratorMapper.deleteById(administrator.getId());
        if (flag > 0) return 1;
        else return 0;
    }

    //查询所有高层
    @Override
    public List<Administrator> selectAll(){
        List<Administrator> message= administratorMapper.selectList(null);
        return message;
    }

    //更改
    @Override
    public Integer Update (Administrator administrator)
    {
        int flag=administratorMapper.updateById(administrator);
        if (flag > 0) return 1;
        else return 0;
    }
}
