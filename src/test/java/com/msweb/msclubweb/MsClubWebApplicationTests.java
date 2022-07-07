package com.msweb.msclubweb;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class MsClubWebApplicationTests {

    @Test
    void contextLoads() {
        Date date = new Date();
        System.out.println(date);
    }

}
