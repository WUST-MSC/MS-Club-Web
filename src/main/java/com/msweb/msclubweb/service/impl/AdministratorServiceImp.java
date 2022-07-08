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
        if(flag==1) return 200;
        else return 500;
    }

    //删除社团高层人员
    @Override
    public Integer deleteById( Integer id){
        int flag=administratorMapper.deleteById(id);
        if(flag ==1) return 200;
        else return 500;
    }

    //查询所有高层
    @Override
    public List<Administrator> selectAll(){
        List<Administrator> message= administratorMapper.selectList(null);
        return message;
    }
}
