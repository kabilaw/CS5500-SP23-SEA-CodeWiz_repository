package com.example.activityapp.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ActivityAppWebMvcConfigurer implements WebMvcConfigurer {
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
//        .allowedOrigins("http://localhost:3000")
        .allowedOrigins("https://www.activity-app.site/", "https://activity-app.site/")
        .allowedMethods("GET", "POST")
        .allowCredentials(true)
        .maxAge(3600);
  }
}
