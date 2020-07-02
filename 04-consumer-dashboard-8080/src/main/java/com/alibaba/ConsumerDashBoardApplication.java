package com.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author shenmeng
 * EnableFeignClients开启feign客户端
 * EnableCircuitBreaker 开启熔断器
 * EnableHystrixDashboard 开启dashboard
 */
@EnableHystrixDashboard
@EnableFeignClients
@EnableCircuitBreaker
@SpringBootApplication
public class ConsumerDashBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerDashBoardApplication.class, args);
	}

}
