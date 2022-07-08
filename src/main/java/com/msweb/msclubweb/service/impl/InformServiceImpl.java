package com.msweb.msclubweb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
    public int addInform(Inform inform) {
        //校验信息不为空
        /*String title = inform.getTitle();
        if (title == null || title.length() == 0) return 400;
        String introduction = inform.getIntroduction();
        if (introduction == null || introduction.length() == 0) return 400;*/
        Date date = new Date();
        //封装时间数据
        inform.setTime(date);
        //写入数据库
        int insert = informMapper.insert(inform);
        if(insert == 1) return 200;
        else return 500;
    }

    @Override
    public Map<String, Object> selectByTitle(Inform inform) {
        HashMap<String, Object> map = new HashMap<>();

        LambdaQueryWrapper<Inform> one = new LambdaQueryWrapper<>();
        one.eq(inform.getTitle()!=null&&inform.getTitle().length()!=0,
                Inform::getTitle,
                inform.getTitle());

        Inform inform1 = informMapper.selectOne(one);
        if(inform1!=null){
            map.put("data",inform1);
            map.put("code",200);
        }else{
            map.put("code",500);
        }
        return map;
    }

    @Override
    public int deleteByTitle(Inform inform) {
        LambdaQueryWrapper<Inform> one = new LambdaQueryWrapper<>();
        one.eq(inform.getTitle()!=null&&inform.getTitle().length()!=0,
                Inform::getTitle,
                inform.getTitle());
        int delete = informMapper.delete(one);
        if(delete==1){
            return 200;
        }else{
            return 500;
        }
    }

    //分页模糊查询
    @Override
    public Map<String,Object> selectPage(long currentPage, long pageSize,Inform inform) {
        HashMap<String, Object> map = new HashMap<>();
        LambdaQueryWrapper<Inform> one = new LambdaQueryWrapper<>();
        one.like(inform.getTitle()!=null&&inform.getTitle().length()!=0,
                Inform::getTitle,
                inform.getTitle());
        Page<Inform> page = new Page<>(currentPage, pageSize);
        informMapper.selectPage(page, one);

        long totalInfoNum = page.getTotal();
        long totalPageNum = page.getPages();
        List<Inform> records = page.getRecords();

        map.put("totalInfoNum",totalInfoNum);
        map.put("totalPageNum",totalPageNum);
        map.put("records",records);
        return map;
    }

}



