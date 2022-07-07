package com.msweb.msclubweb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msweb.msclubweb.domain.Authors;
import com.msweb.msclubweb.service.AuthorsService;
import com.msweb.msclubweb.mapper.AuthorsMapper;
import org.springframework.stereotype.Service;

/**
* @author 86189
* @description 针对表【authors】的数据库操作Service实现
* @createDate 2022-07-07 09:25:53
*/
@Service
public class AuthorsServiceImpl extends ServiceImpl<AuthorsMapper, Authors>
    implements AuthorsService{

}




