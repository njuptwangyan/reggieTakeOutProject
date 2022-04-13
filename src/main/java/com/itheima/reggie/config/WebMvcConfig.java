package com.itheima.reggie.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 设置静态资源映射
     * @param registry
     *
     * 在springboot中处理静态资源映射问题：改变springboot默认的静态资源映射(默认静态资源是放到classpath:/static/、classpath:/public/、
     * classpath:/resources/、classpath:/META-INF/resources/),第一种方式：定义一个配置类实现WebMvcConfigurer接口重写addResourceHandlers
     * 方法，添加资源访问路径和静态资源存放路径。 第二种方法：使用配置文件配置，spring.mvc.static-path-pattern用来配置访问的前缀
     * spring.resources.static-locations配置静态资源存放路径
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("开始进行静态资源映射...");
        //addResourceHandler添加资源处理器  参数是请求的路径
        //addResourceLocations添加资源路径
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
    }
}
