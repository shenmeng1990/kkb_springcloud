package com.alibaba.controller;

import com.alibaba.model.Depart;
import com.alibaba.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author shenmeng
 * @Date 2020/6/22
 **/
@RestController
@RequestMapping("/provider/depart")
public class DepartController {

    @Autowired
    private DepartService service;
    @Autowired
    private DiscoveryClient client;

    @PostMapping
    public boolean saveDepartHandler(@RequestBody Depart depart){
        return service.saveDepart(depart);
    }

    @DeleteMapping("/{id}")
    public boolean deleteDepartHandler(@PathVariable("id") Integer id){
        return service.deleteDepartById(id);
    }

    @PutMapping
    public boolean updateDepartHandler(@RequestBody Depart depart){
        return service.updateDepart(depart);
    }

    @GetMapping("/{id}")
    public Depart getDepartHandler(@PathVariable("id") Integer id){
        return service.getDepartById(id);
    }

    @GetMapping
    public List<Depart> listDepartHandler(){
        return service.listDeparts();
    }

    @GetMapping("/discovery")
    public List<String> discoveryHandler(){
        List<String> services = client.getServices();
        for (String service : services) {
            //获取当前遍历微服务名称的所有提供者主机
            List<ServiceInstance> instances = client.getInstances(service);
            //遍历所有提供者主机的详情
            for (ServiceInstance instance : instances) {
                //获取当前提供者的唯一标识
                String serviceId = instance.getServiceId();
                //获取当前提供者主机的host
                String host = instance.getHost();
                //获取instanceId
                String instanceId = instance.getInstanceId();
                int port = instance.getPort();
                Map<String, String> metadata = instance.getMetadata();
                System.out.println("serviceId:"+serviceId);
                System.out.println("host:"+host);
                System.out.println("instanceId:"+instanceId);
                System.out.println("port:"+port);
                System.out.println("metadata:"+metadata);
            }
        }
        return services;
    }

}
