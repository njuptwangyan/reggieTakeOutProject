package com.itheima.reggie.config;

import com.itheima.reggie.common.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

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

    /**
     * 扩展mvc框架转换器
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //创建消息转换器对象
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        //设置对象转换器，底层使用Jackson将Java对象转为json
        messageConverter.setObjectMapper(new JacksonObjectMapper());
        //将上面的消息转换器对象追加到mvc框架的转换器集合中
        converters.add(0,messageConverter);  //0------>将我们自定义的转换器放到第一位，在使用的时候就会使用我们自定义的转换器
    }
}
