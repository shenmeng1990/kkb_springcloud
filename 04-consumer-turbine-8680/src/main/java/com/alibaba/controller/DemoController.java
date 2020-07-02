package com.alibaba.controller;

import com.alibaba.model.Depart;
import com.alibaba.service.DepartService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author shenmeng
 * @Date 2020/6/22
 **/
@RestController
@RequestMapping("/consumer/depart")
public class DemoController {

    @Autowired
    private DepartService service;

    @PostMapping
    public boolean saveHandler(@RequestBody Depart depart){
        return service.saveDepart(depart);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteHandler(@PathVariable("id") Integer id){
        return service.deleteDepartById(id);
    }

    @PutMapping
    public Boolean updateHandler(@RequestBody Depart depart){
        return service.updateDepart(depart);
    }

    /**
     * 指定该方法使用服务降级，即当处理器方法再运行过程中出现了异常
     * 无法给客户端正常的响应时，就会调用fallbackMethod指定的方法
     * commandProperties 设置hystrix超时时间
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "getHystrixHandler",
            commandProperties = @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="2000"))
    @GetMapping("/{id}")
    public Depart getHandler(@PathVariable("id") Integer id){
        return  service.getDepartById(id);
    }

    /**
     * 服务降级方法 响应给服务端的备选方案
     * @return
     */
    public Depart getHystrixHandler(@PathVariable("id") Integer id){
        Depart depart = new Depart();
        depart.setId(id);
        depart.setName("no this depart -- method");
        return depart;
    }

    @GetMapping
    public List<Depart> listHandler(){
        return service.listDeparts();
    }


}
