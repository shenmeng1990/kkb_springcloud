package com.alibaba.controller;

import com.alibaba.producer.TopicProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author shenmeng
 * @Date 2020/7/7
 **/
@RestController
public class TopicController {

    @Autowired
    private TopicProducer topicProducer;

    @PostMapping("/msg/send")
    public String sendHandler(@RequestParam("msg") String msg){
        return topicProducer.sendMsg(msg);
    }

}
