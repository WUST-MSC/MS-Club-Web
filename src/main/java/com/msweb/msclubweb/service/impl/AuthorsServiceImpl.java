package com.msweb.msclubweb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msweb.msclubweb.domain.Authors;
import com.msweb.msclubweb.service.AuthorsService;
import com.msweb.msclubweb.mapper.AuthorsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 86189
* @description 针对表【authors】的数据库操作Service实现
* @createDate 2022-07-07 09:25:53
*/
@Service
public class AuthorsServiceImpl extends ServiceImpl<AuthorsMapper, Authors>
    implements AuthorsService{
    @Autowired
    private AuthorsMapper authorsMapper;

    @Override
    public Map<String, Object> addAuthor(List<Authors> authorsList) {
        HashMap<String, Object> map = new HashMap<>();
        ArrayList<Integer> authorIds = new ArrayList<>();

        for(int i = 0;i<authorsList.size();i++){
            int insert = authorsMapper.insert(authorsList.get(i));
            if(insert!=1){
                map.put("code","500");
                return map;
            }
            //获取id
            authorIds.add(authorsList.get(i).getId());
        }
        map.put("code",200);
        map.put("authorIds",authorIds);
        return map;
    }

    @Override
    public List<Authors> selectByIds(List<Integer> authorIds) {
        ArrayList<Authors> authors = new ArrayList<>();
        for(int i =0;i<authorIds.size();i++){
            Authors mid = authorsMapper.selectById(authorIds.get(i));
            authors.add(mid);
        }
        return authors;
    }

    @Override
    public int deleteById(List<Integer> authorIds) {
        for(int i = 0;i<authorIds.size();i++){
            int flag = authorsMapper.deleteById(authorIds.get(i));
            if(flag!=1) return 500;
        }
        return 200;
    }
}




