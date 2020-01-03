package com.example.demo1.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//拦截器
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private SesstionInterceptor sesstionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //("/**")是希望全部页面都经过拦截器
        registry.addInterceptor(sesstionInterceptor).addPathPatterns("/**");//SesstionInterceptor为自己创建的类名

    }
}
