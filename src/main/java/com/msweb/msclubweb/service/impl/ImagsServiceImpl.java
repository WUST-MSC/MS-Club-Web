package com.msweb.msclubweb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msweb.msclubweb.domain.Imags;
import com.msweb.msclubweb.service.ImagsService;
import com.msweb.msclubweb.mapper.ImagsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 86189
* @description 针对表【imags】的数据库操作Service实现
* @createDate 2022-07-07 09:26:00
*/
@Service
public class ImagsServiceImpl extends ServiceImpl<ImagsMapper, Imags>
    implements ImagsService{
    @Autowired
    private ImagsMapper imagsMapper;

    //添加img
    @Override
    public int addImg(Imags imags) {
        int insert = imagsMapper.insert(imags);
        if(insert == 1) return 200;
        else return 500;
    }
}




