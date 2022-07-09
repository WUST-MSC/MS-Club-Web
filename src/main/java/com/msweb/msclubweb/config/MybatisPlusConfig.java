package com.msweb.msclubweb.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor in = new MybatisPlusInterceptor();//创建
        //添加分页专用拦截器，后续可以继续添加其他拦截器
        in.addInnerInterceptor(new PaginationInnerInterceptor());
        return in;
    }
}