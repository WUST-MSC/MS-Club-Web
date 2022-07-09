package com.msweb.msclubweb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msweb.msclubweb.domain.*;
import com.msweb.msclubweb.mapper.HonorMapper;
import com.msweb.msclubweb.service.HonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HonorServiceImpl extends ServiceImpl<HonorMapper, Honor> implements HonorService {

    @Autowired
    HonorMapper honorMapper;

    //添加荣誉
    @Override
    public Integer AddHonor(Honor honor){

        int flag=honorMapper.insert(honor);
        if (flag > 0) return 1;
        else return 0;
    }

    //删除荣誉
    @Override
    public Integer deleteByTitle(Honor honor){
        LambdaQueryWrapper<Honor> one = new LambdaQueryWrapper<>();
        one.eq(honor.getTitle()!=null, Honor::getTitle, honor.getTitle());
        int flag = honorMapper.delete(one);
        if(flag>0) return 1;
        else return 0;
    }

    //分页分类别 查询
    @Override
    public BackPage<Honor> selectPage (PageSelect pageSelect){
        //每一页展示number个数据
        Integer number=10;
        BackPage<Honor> HonorBackPage = new BackPage<>();
        int pageNumber= pageSelect.getPageNumber();
        int flag=pageSelect.getFlag();
        //判断是否需要分页查询
        if(pageNumber>0)
        {
            if (flag>0)
            {
                QueryWrapper<Honor> one = new QueryWrapper<>();
                one.ge("flag",flag);

                Page<Honor> HonorPage=new Page<>(pageNumber,number);
                IPage<Honor> HonorIPage= page(HonorPage,one);
                //数据封装
                HonorBackPage.setContentList(HonorIPage.getRecords());
                HonorBackPage.setCurrentPage(HonorIPage.getCurrent());
                HonorBackPage.setTotalPage(HonorIPage.getPages());
                HonorBackPage.setTotalNum(HonorIPage.getTotal());
            }
            else
            {
                Page<Honor> HonorPage=new Page<>(pageNumber,number);
                IPage<Honor> HonorIPage= page(HonorPage,null);
                //数据封装
                HonorBackPage.setContentList(HonorIPage.getRecords());
                HonorBackPage.setCurrentPage(HonorIPage.getCurrent());
                HonorBackPage.setTotalPage(HonorIPage.getPages());
                HonorBackPage.setTotalNum(HonorIPage.getTotal());
            }

        }
        else
        {
            QueryWrapper<Honor> one = new QueryWrapper<>();
            one.ge("flag",flag);
            HonorBackPage.setContentList(honorMapper.selectList(one));
        }
        return HonorBackPage;
    }


    //查询所有荣誉
    @Override
    public List<Honor> selectAll(){
        List<Honor> message=honorMapper.selectList(null);
        return message;
    }


    //按照id查询
    @Override
    public Honor selectByTitle(Honor honor){
        LambdaQueryWrapper<Honor> one = new LambdaQueryWrapper<>();
        one.eq(honor.getTitle()!=null, Honor::getTitle, honor.getTitle());
        return honorMapper.selectOne(one);
    }

    @Override
    public Integer Update (Honor honor){
        Integer flag=honorMapper.updateById(honor);
        if (flag > 0) return 1;
        else return 0;
    }
}
