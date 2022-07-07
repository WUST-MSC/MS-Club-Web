package com.msweb.msclubweb.controller;

import com.msweb.msclubweb.service.InformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class testController {
    @Autowired
    private InformService informService;

    public void test(){
    }
}
