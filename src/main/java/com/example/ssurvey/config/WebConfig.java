package com.example.ssurvey.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("http://localhost:3000")  // 리액트 서버 , http://localhost:3000 여기에 대한 요청은 다 허용해줘라
		.allowedMethods("GET", "POST", "DELETE", "PUT");
	}

	
	
}
