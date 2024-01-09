package com.hzcu_fleakmarket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyStaticConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 访问路径以 “/mystatic” 开头时，会去 “mystatic” 路径下找静态资源
        registry
                .addResourceHandler("/upload/**")
                .addResourceLocations("classpath:/upload/");
    }

}