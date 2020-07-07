package com.alibaba.config;

import com.alibaba.balance.CustomRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author shenmeng
 * @Date 2020/6/22
 **/
@Configuration
public class DepartConfig {

  /*  //修改负载均衡策略为随机
    @Bean
    public IRule loadBalanceRule(){
        return new RandomRule();
    }*/

    /**
     * 使用自定义的负载均衡策略
     */
    @Bean
    public IRule loadBalanceRule(){
        List<Integer> excludePorts = new ArrayList<>();
        excludePorts.add(8082);
        return new CustomRule(excludePorts);
    }

}
