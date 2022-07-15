package com.msweb.msclubweb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msweb.msclubweb.domain.Manager;
import com.msweb.msclubweb.service.ManagerService;
import com.msweb.msclubweb.mapper.ManagerMapper;
import org.springframework.stereotype.Service;

/**
* @author 86189
* @description 针对表【manager】的数据库操作Service实现
* @createDate 2022-07-14 08:56:27
*/
@Service
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager>
    implements ManagerService{
    private ManagerMapper managerMapper;

}




