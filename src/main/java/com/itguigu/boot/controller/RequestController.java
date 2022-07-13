package com.itguigu.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RequestController {

    @GetMapping("/goto")
    public String method(HttpServletRequest request){
        request.setAttribute("msg", "成功了");
        request.setAttribute("code", "8080");
        return "forward:success";
    }

    @ResponseBody
    @RequestMapping("/success")
    public String success(@RequestAttribute("msg") String msg,
                          @RequestAttribute("code")String code){
        return msg+code;
    }
}
