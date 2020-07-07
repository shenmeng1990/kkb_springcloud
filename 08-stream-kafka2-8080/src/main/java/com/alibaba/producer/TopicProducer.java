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
@EnableBinding({Source.class,CustomSource.class})//将MQ与生产者类通过消息管道绑定，source相当于输出管道
public class TopicProducer {

    //必须使用ByName的方式自动注入，一个管道对应一个主题
    @Autowired
    @Qualifier(Source.OUTPUT)
    private MessageChannel channel;

    @Autowired
    @Qualifier(CustomSource.CHANNEL_NAME)
    private MessageChannel customChannel;

    public String sendMsg(String msg){
        //将消息写入到两个管道中，也就对应着连个主题
        channel.send(MessageBuilder.withPayload(msg).build());
        customChannel.send(MessageBuilder.withPayload(msg).build());
        return msg;
    }
}
