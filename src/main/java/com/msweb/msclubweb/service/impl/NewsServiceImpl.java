package com.msweb.msclubweb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msweb.msclubweb.common.Result;
import com.msweb.msclubweb.domain.Imags;
import com.msweb.msclubweb.domain.Inform;
import com.msweb.msclubweb.domain.News;
import com.msweb.msclubweb.mapper.ImagsMapper;
import com.msweb.msclubweb.service.*;
import com.msweb.msclubweb.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
* @author 86189
* @description 针对表【news】的数据库操作Service实现
* @createDate 2022-07-07 09:26:05
*/
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News>
    implements NewsService{
    @Autowired
    private NewsMapper newsMapper;
    @Autowired
    private ImagsService imagsService;
    @Autowired
    private NewsImagsService newsImagsService;

    //定义标识符
    @Value("${identifier.start}")
    private String start;
    @Value("${identifier.end}")
    private String end;
    @Value("${identifier.noExist}")
    private String noExist;

    @Override
    public int addNews(News news) {
        //1.扫描文本提取List<String> imgsname
        StringBuilder text = new StringBuilder(news.getIntroduction());
        ArrayList<Integer> imgIds = new ArrayList<>();
        int first = 0,last = 0;
        while(text.indexOf(end,first)!=-1&&text.indexOf(start,first)!=-1){
            first = text.indexOf(start,first);
            last = text.indexOf(end,first);
            //System.out.println(first+ "  "+ last);
            String name = text.substring(first+1,last);
            //开始替换标识符为图片src
            Imags imags = imagsService.selectByName(name);
            if(imags!=null){//图片存在
                imgIds.add(imags.getId());
                text.replace(first+1,last,imags.getSrc());
                first = first + imags.getSrc().length();
            }else{//图片不存在
                text.replace(first+1,last,noExist);
                first = first + noExist.length();
            }
        }
        //2.提交数据库（为主表，先主表后从表）
        Date date = new Date();
        news.setTime(date);
        news.setIntroduction(text.toString());
        int insert = newsMapper.insert(news);
        if(insert==0) {//提交失败，返回
            return -1;
        }
        //3.绑定图片
        int flag = newsImagsService.addByNewsidAndImagid(news.getId(), imgIds);
        if(flag != 200){
            return -1;
        }else{
            return news.getId();
        }
    }

    @Override
    public int deleteNews(News news) {
        return 0;
    }

    @Override
    public Result<News> selectByTime(News news) {
        News news1 = newsMapper.selectBytime(news.getTime());
        return news1!=null?Result.sqlError():Result.success(news1);
    }

    @Override
    public Map<String, Object> selectPage(long currentPage, long pageSize, News news) {
        HashMap<String, Object> map = new HashMap<>();
        LambdaQueryWrapper<News> one = new LambdaQueryWrapper<>();
        one.like(news.getTitle()!=null&&news.getTitle().length()!=0,
                News::getTitle,
                news.getTitle());
        Page<News> page = new Page<>(currentPage, pageSize);
        newsMapper.selectPage(page,one);

        long totalInfoNum = page.getTotal();
        long totalPageNum = page.getPages();
        List<News> records = page.getRecords();

        map.put("totalInfoNum",totalInfoNum);
        map.put("totalPageNum",totalPageNum);
        map.put("records",records);
        return map;
    }
}




