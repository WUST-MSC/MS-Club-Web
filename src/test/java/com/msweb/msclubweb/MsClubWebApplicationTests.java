package com.msweb.msclubweb;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.msweb.msclubweb.common.PageParam;
import com.msweb.msclubweb.domain.Authors;
import com.msweb.msclubweb.domain.Imags;
import com.msweb.msclubweb.domain.News;
import com.msweb.msclubweb.mapper.AuthorsMapper;
import com.msweb.msclubweb.mapper.NewsMapper;
import com.msweb.msclubweb.util.PatternHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@SpringBootTest
class MsClubWebApplicationTests {

    @Autowired
    private AuthorsMapper authorsMapper;

    @Autowired
    private NewsMapper newsMapper;

    @Test
    void contextLoads() throws InterruptedException {
        String a = " sadsadjsadnanxcx`sadas+nasdnnasdsn`asdasdas+`sadnasjkn+``++`+";
        String b = "abcde";
        String c = "```````````````";
        String d = "++++++++++++";
        String start = "`",end="+";

        ArrayList<String> imgName = new ArrayList<>();
        StringBuilder text = new StringBuilder(a);
        int first = 0,last = 0;
        while(text.indexOf(end,first)!=-1&&text.indexOf(start,first)!=-1){
            first = text.indexOf(start,first);
            last = text.indexOf(end,first);
            //System.out.println(first+ "  "+ last);
            String name = text.substring(first+1,last);
            imgName.add(name);
            //开始替换
            LambdaQueryWrapper<Imags> mid = new LambdaQueryWrapper<>();
            mid.eq(Imags::getName,name);
            text.replace(first+1,last,b);
            first = first + b.length();
        }
        System.out.println(text);
    }

    @Test
    public void test1() throws ParseException {
        Date parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse("2022-07-09 21:04:25.262");
        News news = newsMapper.selectBytime(parse);
        System.out.println(news);
    }
}
