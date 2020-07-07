package com.alibaba;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * @author shenmeng
 */
@EnableZuulProxy
@SpringCloudApplication
public class ZuulFilter9000App {

    public static void main(String[] args) {
        SpringApplication.run(ZuulFilter9000App.class, args);
    }

    /**
     * 设置zuul对consumer的负载均衡策略为随机策略
     * @return
     */
    @Bean
    public IRule loadBanlanceRule(){
        return new RandomRule();
    }

}
