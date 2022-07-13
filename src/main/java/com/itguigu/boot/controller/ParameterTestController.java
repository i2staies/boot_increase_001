package com.itguigu.boot.controller;

import jdk.nashorn.internal.objects.annotations.Getter;
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



//     1. 语法： /cars/sell;low=34;brand=byd,audi,yd
//     2. springboot默认是禁用矩阵变量的功能，需要手动开启
//     springmvc的自动配置类WebMvcAutoConfiguration
//          里面有个configurePathMatch（配置路径映射）方法
//              configurePathMatch里面有个UrlPathHelper（url的路径帮助器）
//                  里面有个属性叫removeSemicolonContent（移除分号内容）
//                  Set if ";" (semicolon) content should be stripped from the request URI.
//                  <p>Default is "true".
//                  会将;号后面的路径内容截掉，默认值为true
//    在这并不能直接使用，因为默认加载的配置自动去掉;后面的数据
//    需要自己配置或者重写这个方法

//    3. 手动开启： 原理：对于路径的处理，都是通过使用UrlPathHelper进行解析，
//                      而removeSemicolonContent（移除分号内容，默认为true）就是用来支持矩阵变量的
//                      不用@EnableWebMvc注解。使用@Configuration+WebMvcConfigurer`自定义规则
//                      WebMvcConfigurer是个接口，其中定义了一个方法，就是configurePathMatch，
//                      而在写WebMvcAutoConfigurationAdapter类时，实现了WebMvcConfigurer
//                      定义路径映射规则时，UrlPathHelper用的是默认的，默认移除内容，
//  /cars/sell;low=34;brand=byd,audi,yd
    @GetMapping("/cars/{path}")   //矩阵变量是绑定在路径变量上的，所以controller的访问路径必须要写路径变量
    public Map carsSell(@MatrixVariable("low") String low,
                        @MatrixVariable("brand")String[] brand,
                        @PathVariable("path")String path){
        Map<String,Object> map = new HashMap<>();
        map.put("low", low);
        map.put("brand", brand);
        map.put("path", path);
        return map;
    }

//  /boss/1;age=20/2;age=10
    @GetMapping("/boss/{bossid}/{empid}")
    public Map boss(@MatrixVariable(value = "age",pathVar = "bossid")String bossid,
                    @MatrixVariable(value = "age",pathVar = "empid")String[] empid){
        Map<String,Object> map = new HashMap<>();
        map.put("bossid", bossid);
        map.put("empid", empid);
        return map;
    }
}
