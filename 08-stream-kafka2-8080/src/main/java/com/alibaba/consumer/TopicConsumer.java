package com.alibaba.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
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
public class TopicConsumer {

    @Autowired
    @Qualifier(Sink.INPUT)
    private SubscribableChannel  channel;

    /**
     * 已发布订阅的方式消费主题
     */
    @PostConstruct
    public void pringMsg(){
        channel.subscribe(new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                System.out.println(new String((byte[]) message.getPayload()));
            }
        });
    }

}
