package com.alibaba.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author shenmeng
 * @Date 2020/7/7
 **/
@Component
@EnableBinding(Sink.class)
public class TopicConsumer2 {

    @ServiceActivator(inputChannel = Sink.INPUT)
    public void pringMsg(Object msg){
        System.out.println(msg);
    }

}
