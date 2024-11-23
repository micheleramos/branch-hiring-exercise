package com.example.branch_hiring_exercise.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestTemplateConfig {
    @Bean
    RestTemplateConfig restTemplate() {
	    return new RestTemplateConfig();
	}

}

