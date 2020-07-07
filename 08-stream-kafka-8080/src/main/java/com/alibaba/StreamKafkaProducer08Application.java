package com.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author shenmeng
 */
@EnableFeignClients //开启feign客户端
@SpringBootApplication
public class StreamKafkaProducer08Application {

	public static void main(String[] args) {
		SpringApplication.run(StreamKafkaProducer08Application.class, args);
	}

}
