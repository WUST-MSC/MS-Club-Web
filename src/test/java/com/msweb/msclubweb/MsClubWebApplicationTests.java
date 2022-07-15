package com.msweb.msclubweb;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.msweb.msclubweb.domain.Imags;
import com.msweb.msclubweb.domain.Inform;
import com.msweb.msclubweb.domain.News;
import com.msweb.msclubweb.mapper.AuthorsMapper;
import com.msweb.msclubweb.mapper.NewsMapper;
import com.msweb.msclubweb.service.InformService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class MsClubWebApplicationTests {

    @Autowired
    private AuthorsMapper authorsMapper;

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private InformService informService;

    @Test
    void contextLoads() throws InterruptedException {
        String a = " sads([{adjsadn}])anxcx`sadas+n([{adjsadn}])asdnnasdsn`asd([{adjsadn}])asdas+`sadnasjkn+``++`+([{adjsadn}])";
        String b = "abcde";
        String c = "```````````````";
        String d = "++++++++++++";
        String start = "([{",end="}])";

        ArrayList<String> imgName = new ArrayList<>();
        StringBuilder text = new StringBuilder(a);
        int first = 0,last = 0;
        //news.setBriefIntroduction(text.substring(0,text.indexOf("。")));
        while(text.indexOf(end,first)!=-1&&text.indexOf(start,first)!=-1){
            first = text.indexOf(start,first);
            last = text.indexOf(end,first);
            System.out.println("first = "+first + "last = " +  last);
            //System.out.println(first+ "  "+ last);
            String name = text.substring(first+start.length(),last);
            System.out.println(name);
            String temp = "<img src = \""+name+"\"></img>";
            text.replace(first,last+end.length(),temp);
            first += temp.length();
            //开始替换标识符为图片src
           // Imags imags = imagsService.selectByName(name);
        }
        System.out.println(text);
    }

    @Test
    public void test1() throws ParseException {
        Date parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse("2022-07-09 21:04:25.262");
       // News news = newsMapper.selectBytime(parse);
       // System.out.println(news);
    }

    @Test
    public void test2(){
        /*Inform inform = new Inform();
        inform.setTitle("金威真滴帅，帅帅，金威牛逼");
        inform.setIntroduction("金威是谁，一听名字就知道这个人很帅，见到一定要请他吃饭喝奶茶awa");
        inform.setTime(new Date());
        informService.addInform()*/
    }

    @Test
    public void test3(){
        List<News> news = newsMapper.selectCurrentPage(null, 1, 1);
        System.out.println(news);
    }
}
