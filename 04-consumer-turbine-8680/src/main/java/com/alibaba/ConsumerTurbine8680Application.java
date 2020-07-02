package com.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author shenmeng
 * EnableFeignClients开启feign客户端
 * EnableCircuitBreaker 开启熔断器
 */
@EnableFeignClients
@EnableCircuitBreaker
@SpringBootApplication
public class ConsumerTurbine8680Application {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerTurbine8680Application.class, args);
	}

}
