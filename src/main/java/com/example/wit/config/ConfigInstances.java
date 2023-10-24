package com.example.wit.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigInstances {
    @Bean
    public ModelMapper instanceModelMapper() {
        return new ModelMapper();
    }
}
