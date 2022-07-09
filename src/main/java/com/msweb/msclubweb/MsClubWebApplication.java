package com.msweb.msclubweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.msweb.msclubweb.mapper")
public class MsClubWebApplication {
    //test
    public static void main(String[] args) {
        SpringApplication.run(MsClubWebApplication.class, args);
    }

}
