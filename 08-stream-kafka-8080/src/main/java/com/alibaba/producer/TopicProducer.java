package com.alibaba.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * 消息发送给一个主题的生产者
 * @Author shenmeng
 * @Date 2020/7/7
 **/

@Component
@EnableBinding(Source.class)//将MQ与生产者类通过消息管道绑定
public class TopicProducer {

    //必须使用ByName的方式自动注入，一个管道对应一个主题
    @Autowired
    @Qualifier(Source.OUTPUT)
    private MessageChannel channel;

    public String sendMsg(String msg){
        //通过消息管道发送消息，将消息写入到消息管道，再通过消息管道写入到MQ
        channel.send(MessageBuilder.withPayload(msg).build());
        return msg;
    }
}
