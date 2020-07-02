package com.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @author shenmeng
 */
@SpringCloudApplication
@EnableHystrixDashboard
@EnableTurbine
public class HystrixTurbine8888App {

    public static void main(String[] args) {
        SpringApplication.run(HystrixTurbine8888App.class, args);
    }

}
