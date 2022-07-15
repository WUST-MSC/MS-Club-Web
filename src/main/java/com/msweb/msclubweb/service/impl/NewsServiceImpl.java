package com.msweb.msclubweb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msweb.msclubweb.common.Result;
import com.msweb.msclubweb.domain.*;
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
    @Autowired
    private AuthorsService authorsService;
    @Autowired
    private NewsAuthorService newsAuthorService;
    @Autowired
    private ImgCacheService imgCacheService;

    //定义标识符
    @Value("${identifier.start}")
    private String start;
    @Value("${identifier.end}")
    private String end;
    @Value("${identifier.noExist}")
    private String noExist;
    /**
     * 添加新闻
     * @param
     * @return
     */
    @Override
    public Result<News> addNews(News news) {
        //1.取出数据
        //2.扫描
        StringBuilder text = new StringBuilder(news.getIntroduction());
        ArrayList<Integer> imgIds = new ArrayList<>();//存放imgIds
        //得到缓存
        List<ImgCache> imgCaches = imgCacheService.getImgCache();
        //int index = 0;//记录取到第几个
        int first = 0,last = 0,startLength = start.length(),endLength = end.length();
        while(text.indexOf(end,first)!=-1&&text.indexOf(start,first)!=-1){
            first = text.indexOf(start,first);
            last = text.indexOf(end,first);
            //System.out.println(first+ "  "+ last);
            //找到标签顺序早的在前面
            int index = Integer.parseInt(text.substring(first + startLength, last));
            if(index<=imgCaches.size()){//图片缓存存在
                ImgCache imgCache = imgCaches.get(index-1);
                String src = imgCache.getSrc();//图片资源路径
                //开始替换标识符为图片src
                String sign = "<img src=\""+src+"\" />";
                imgIds.add(imgCache.getImgId());//获取相关图片id
                text.replace(first,last+endLength,sign);
                first = first + src.length();//寻找下一个
            }
            else{//不存在
                text.replace(first,last+endLength,noExist);
                first+= noExist.length();
            }
        }
        news.setIntroduction(text.toString());
        news.setTime(new Date());//设置时间
        //插入news
        newsMapper.insert(news);
        //建立img和news关系
        newsImagsService.addByNewsidAndImagid(news.getId(),imgIds);
        //添加作者
        List<Authors> authors = news.getAuthors();
        List<Integer> authorIds = authorsService.addAuthor(authors);
        //建立关系
        newsAuthorService.addByNewsidAndAuthorid(news.getId(),authorIds);
        //删除缓存
        imgCacheService.deleteCache();
        return Result.success(null);
    }

    //删除新闻
    @Override
    public Result<News> deleteById(Integer id) {
        //1.找到相关图片id
        List<Integer> imgIds = newsImagsService.selectImgIdsByNewId(id);
        newsImagsService.deleteByNewsId(id);//删除从表中对应数据
        if(imgIds.size()!=0){
            //2.删除对应图片
            imagsService.deleteByIds(imgIds);
        }
        //3.删除与对应作者关系
        newsAuthorService.deleteByNewsId(id);
        //4.删除新闻本身
        int delete = newsMapper.deleteById(id);
        return delete==0?Result.sqlError():Result.success(null);
    }

    //查看新闻
    @Override
    public Result<News> selectById(Integer id) {
        News news = newsMapper.selectById(id);
        return news!=null?Result.success(news):Result.sqlError();
    }

    //分页模糊查询
    @Override
    public Map<String, Object> selectPage(Long currentPage, Long pageSize, String condition) {
        HashMap<String, Object> map = new HashMap<>();
        LambdaQueryWrapper<News> one = new LambdaQueryWrapper<>();
        one.like(condition!=null&&condition.length()!=0,
                News::getTitle,
                condition);

        Page<News> page = new Page<>(currentPage, pageSize);
        newsMapper.selectPage(page,one);

        long totalInfoNum = page.getTotal();
        long totalPageNum = page.getPages();
        List<News> records = newsMapper.selectCurrentPage(condition,pageSize,(currentPage-1)*pageSize);

        map.put("totalInfoNum",totalInfoNum);
        map.put("totalPageNum",totalPageNum);
        map.put("records",records);
        return map;
    }
}




