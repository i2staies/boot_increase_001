package com.itguigu.boot.config;

import org.springframework.boot.web.servlet.filter.OrderedHiddenHttpMethodFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

//默认为true，使用代理，也就是单例模式，直接从IOC容器之中取得对象
//false的话，每次调用@Bean标注的方法获取到的对象和IOC容器中的都不一样，是一个新的对象，
@Configuration(proxyBeanMethods = false)

/*矩阵变量方法一： 实现接口*/
public class WebConfig /*implements WebMvcConfigurer*/ {
//    因为jdk8中有WebMvcConfiger的实现方法，所以在此只需要修改要修改的方法即可


//    设置自定义的_method
    @Bean
//    在此配置hiddenHttpMethodFilter组件，则默认的OrderedHiddenHttpMethodFilter组件不会加载
//
    public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
//        虽然在源码当中public static final String DEFAULT_METHOD_PARAM = "_method";使用final修饰
//        private String methodParam = DEFAULT_METHOD_PARAM;
//        但在源码中却提供了修改methodParam的set方法setMethodParam
        hiddenHttpMethodFilter.setMethodParam("method_A");
        return hiddenHttpMethodFilter;
    }

//    方法二：
    @Bean  //WebMvcConfiger
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {
                UrlPathHelper urlPathHelper = new UrlPathHelper();
//              不移除;后面的内容，矩阵变量生效
                urlPathHelper.setRemoveSemicolonContent(false);
                configurer.setUrlPathHelper(urlPathHelper);
            }
        };
    }


/*
    矩阵变量方法一：
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper = new UrlPathHelper();
//        不移除;后面的内容，矩阵变量生效
        urlPathHelper.setRemoveSemicolonContent(false);
        configurer.setUrlPathHelper(urlPathHelper);
    }*/
}
