package com.itguigu.boot.config;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ParameterTestController {
    @GetMapping(value = "car/{id}/owner/{username}")
    public Map<String,Object> getCar(@PathVariable("id")String id,
                                     @PathVariable("username")String username,
                                     @PathVariable Map<String,String> pv,
                                     @RequestHeader("User-Agent")String userAgent,
                                     @RequestHeader Map<String,String> headers,
                                     @RequestParam("age")String age,
                                     @RequestParam("inters")List<String> inters,
                                     @RequestParam Map<String,String> params,
                                     @CookieValue("Idea-cf4d483c") String Ideacf4d483c,
                                     @CookieValue("Idea-cf4d483c")Cookie cookie){
        Map<String, Object> map = new HashMap<>();
//        map.put("id", id);
//        map.put("username", username);
//        map.put("pv", pv);
//        map.put("userAgent", userAgent);
//        map.put("header", headers);
        map.put("params", params);
        map.put("Idea-cf4d483c", Ideacf4d483c );
        System.out.println("cooke.getname = " + cookie.getName() + "cookie.getValue" + cookie.getValue());
        return map;
    }

    @PostMapping("/save")
//    @RequestBody将post发送的请求以字符串的形式赋值给content
    public String postMethod(@RequestBody String content){
        return content;
    }
}
