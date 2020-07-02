package com.alibaba.controller;

import com.alibaba.model.Depart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Author shenmeng
 * @Date 2020/6/22
 **/
@RestController
@RequestMapping("/depart")
public class DemoController {

    //使用微服务名称
    private static final String SERVICE_LOCATION="http://provider8081-1/depart/";

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public boolean saveHandler(@RequestBody Depart depart){
        return restTemplate.postForObject(SERVICE_LOCATION, depart, Boolean.class);
    }

    @DeleteMapping("/{id}")
    public void deleteHandler(@PathVariable("id") Integer id){
        restTemplate.delete(SERVICE_LOCATION+id);
    }

    @PutMapping
    public void updateHandler(@RequestBody Depart depart){
        restTemplate.put(SERVICE_LOCATION,depart);
    }

    @GetMapping("/{id}")
    public Depart getHandler(@PathVariable("id") Integer id){
        System.out.println(SERVICE_LOCATION+id);
        return restTemplate.getForObject(SERVICE_LOCATION+id,Depart.class);
    }

    @GetMapping
    public List<Depart> listHandler(){
        return restTemplate.getForObject(SERVICE_LOCATION,List.class);
    }


}
