package com.example.demo.domain;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**")
                //로컬
//                .addResourceLocations("file:C:/Users/USER/Desktop/assets/");
        //서버
                .addResourceLocations("file:/app/uploads/assets/");
    }
}
