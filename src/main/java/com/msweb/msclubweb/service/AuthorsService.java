package com.msweb.msclubweb.service;

import com.msweb.msclubweb.domain.Authors;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author 86189
* @description 针对表【authors】的数据库操作Service
* @createDate 2022-07-07 09:25:53
*/
public interface AuthorsService extends IService<Authors> {
    //添加作者返回id
    List<Integer> addAuthor(List<Authors> authorsList);
    //查找通过ids
    List<Authors> selectByIds(List<Integer> authorIds);
    //删除通过id
    boolean deleteById(List<Integer> authorIds);
}
