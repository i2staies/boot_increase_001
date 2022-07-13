package com.itguigu.boot.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RequestControoler {

    @GetMapping("/one")
    public String method(){
        return "success";
    }
}
