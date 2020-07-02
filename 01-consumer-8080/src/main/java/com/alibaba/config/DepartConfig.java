package com.alibaba.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author shenmeng
 * @Date 2020/6/22
 **/
@Configuration
public class DepartConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
