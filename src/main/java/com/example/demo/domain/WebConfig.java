package com.example.demo.domain;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //로컬
        //.addResourceLocations("file:C:/Users/USER/Desktop/assets/");


        //서버
        registry.addResourceHandler("/assets/**")
                .addResourceLocations("file:/app/uploads/assets/");


        registry.addResourceHandler("/files/**")
                .addResourceLocations("file:/app/uploads/files/");
    }
}
