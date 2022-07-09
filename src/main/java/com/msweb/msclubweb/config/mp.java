package com.msweb.msclubweb.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;

public class mp {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {

        PaginationInnerInterceptor page = new PaginationInnerInterceptor(DbType.MYSQL);
        //单次查询最大的数量   如果我查10条，返回还是5条。
        page.setMaxLimit(10L);
        //溢出总页数后是否做处理（默认不做，true表示做处理，回到首页） false  继续请求
        page.setOverflow(false);
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //设置数据库类型为mysql
        interceptor.addInnerInterceptor(page);
        return interceptor;
    }

}
