package com.msweb.msclubweb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msweb.msclubweb.domain.Imags;
import com.msweb.msclubweb.service.ImagsService;
import com.msweb.msclubweb.mapper.ImagsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Imags selectByName(String imgName) {
        LambdaQueryWrapper<Imags> one = new LambdaQueryWrapper<>();
        one.eq(Imags::getName,imgName);

        Imags imags = imagsMapper.selectOne(one);
        return imags;
    }

    @Override
    public Map<String, Object> selectByNames(List<String> imgNames) {
        HashMap<String, Object> map = new HashMap<>();
        ArrayList<Integer> imgIds = new ArrayList<>();

        for(int i = 0;i<imgNames.size();i++){
            //找到对应imgId
            LambdaQueryWrapper<Imags> mid = new LambdaQueryWrapper<>();
            mid.eq(Imags::getName,imgNames.get(i));

            Imags imgs1 = imagsMapper.selectOne(mid);
            if(imgs1!=null){
                imgIds.add(imgs1.getId());
            }else{
                //未找到对应图片
                map.put("code",404);
                return map;
            }
        }
        //找完后封装返回
        map.put("imgIds",imgIds);
        map.put("code",200);
        return map;
    }

    @Override
    public int deleteById(List<Integer> imgIds) {
        for(int i = 0;i<imgIds.size();i++){
            int flag = imagsMapper.deleteById(imgIds.get(i));
            if(flag!=1) return 500;
        }
        return 200;
    }

}




