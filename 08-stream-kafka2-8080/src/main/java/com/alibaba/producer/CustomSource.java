package com.alibaba.producer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 自定管道
 * @Author shenmeng
 * @Date 2020/7/7
 **/
public interface CustomSource {

    String CHANNEL_NAME="customOutput";

    @Output(CustomSource.CHANNEL_NAME)
    MessageChannel output();
}
