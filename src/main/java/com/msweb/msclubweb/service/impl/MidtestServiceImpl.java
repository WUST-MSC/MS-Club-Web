package com.msweb.msclubweb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msweb.msclubweb.domain.Midtest;
import com.msweb.msclubweb.service.MidtestService;
import com.msweb.msclubweb.mapper.MidtestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 86189
* @description 针对表【midtest】的数据库操作Service实现
* @createDate 2022-07-14 15:11:21
*/
@Service
public class MidtestServiceImpl extends ServiceImpl<MidtestMapper, Midtest>
    implements MidtestService{
    @Autowired
    private MidtestMapper midtestMapper;
}




