package com.alibaba.consumer;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

/**
 * @Author shenmeng
 * @Date 2020/7/7
 **/
@Component
@EnableBinding(Sink.class)
public class TopicConsumer3 {

    @StreamListener(Sink.INPUT)
    public void pringMsg(Object msg){
        System.out.println(msg);
    }

}
