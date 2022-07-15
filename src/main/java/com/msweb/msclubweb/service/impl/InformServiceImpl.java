package com.msweb.msclubweb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
        //1.封装时间数据和概要
        Date date = new Date();
        inform.setTime(date);
        String introduction = inform.getIntroduction();
        int index = introduction.indexOf("。");
        if(index!=-1){
            inform.setOutline(introduction.substring(0,introduction.indexOf("。")));//截取第一段文字
        }else{
            inform.setOutline(introduction);
        }

        //2.写入数据库
        int insert = informMapper.insert(inform);
        return insert == 0?Result.sqlError():Result.success(null);
    }

    //通过id查找
    @Override
    public Result<Inform> selectById(Integer id) {
        Inform inform = informMapper.selectById(id);
        return inform==null?Result.notFound():Result.success(inform);
    }

    @Override
    public Result<Inform> deleteById(Integer id) {
        //1.删除
        int delete = informMapper.deleteById(id);
        return delete==0?Result.sqlError():Result.success(null);
    }

    //分页模糊查询
    @Override
    public Map<String, Object> selectPage(Long currentPage, Long pageSize, String condition) {
        HashMap<String, Object> map = new HashMap<>();
        //对标题，内容模糊查询
        LambdaQueryWrapper<Inform> one = new LambdaQueryWrapper<>();
        one.like(condition!=null&&condition.length()!=0,
                Inform::getIntroduction,
                condition);
        Page<Inform> page = new Page<>(currentPage, pageSize);
        informMapper.selectPage(page, one);

        long totalInfoNum = page.getTotal();
        long totalPageNum = page.getPages();
        List<Inform> records = informMapper.selectCurrentPage(condition,pageSize,(currentPage-1)*pageSize);

        map.put("totalInfoNum",totalInfoNum);
        map.put("totalPageNum",totalPageNum);
        map.put("records",records);
        return map;
    }
}



