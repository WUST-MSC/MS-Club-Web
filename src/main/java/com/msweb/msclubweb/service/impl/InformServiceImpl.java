package com.msweb.msclubweb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msweb.msclubweb.common.PageParam;
import com.msweb.msclubweb.common.Result;
import com.msweb.msclubweb.domain.Inform;
import com.msweb.msclubweb.service.InformService;
import com.msweb.msclubweb.mapper.InformMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 86189
* @description 针对表【inform】的数据库操作Service实现
* @createDate 2022-07-07 09:24:47
*/
@Service
public class InformServiceImpl extends ServiceImpl<InformMapper, Inform>
    implements InformService{
    @Autowired
    private InformMapper informMapper;
    //写通知
    @Override
    public Result<Inform> addInform(Inform inform) {
        //逻辑合理性判断
        //1.封装时间数据
        Date date = new Date();
        inform.setTime(date);
        //2.写入数据库
        int insert = informMapper.insert(inform);
        return insert == 0?Result.sqlError():Result.success(null);
    }

    @Override
    public Result<Inform> selectByTime(Inform inform) {
        LambdaQueryWrapper<Inform> one = new LambdaQueryWrapper<>();
        one.eq(inform.getTime()!=null,
                Inform::getTitle,
                inform.getTitle());

        Inform inform1 = informMapper.selectOne(one);
        return inform1==null?Result.notFound():Result.success(inform1);
    }

    @Override
    public Result<Inform> deleteByTime(Inform inform) {
        //1.删除
        LambdaQueryWrapper<Inform> one = new LambdaQueryWrapper<>();
        one.eq(inform.getTime()!=null,
                Inform::getTitle,
                inform.getTitle());
        int delete = informMapper.delete(one);
        return delete==0?Result.sqlError():Result.success(null);
    }

    //分页模糊查询
    @Override
    public
    Page<Inform> selectPage(PageParam pageParam, Inform inform) {
        HashMap<String, Object> map = new HashMap<>();
        LambdaQueryWrapper<Inform> one = new LambdaQueryWrapper<>();
        one.like(inform.getTitle()!=null&&inform.getTitle().length()!=0,
                Inform::getTitle,
                inform.getTitle());
        Page<Inform> page = new Page<>(pageParam.getPage(), pageParam.getSize());
        informMapper.selectPage(page, one);

        /*long totalInfoNum = page.getTotal();
        long totalPageNum = page.getPages();
        List<Inform> records = page.getRecords();

        map.put("totalInfoNum",totalInfoNum);
        map.put("totalPageNum",totalPageNum);
        map.put("records",records);*/
        return page;
    }

}



