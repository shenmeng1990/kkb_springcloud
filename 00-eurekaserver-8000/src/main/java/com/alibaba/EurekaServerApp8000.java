package com.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author shenmeng
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApp8000 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApp8000.class, args);
    }

}
