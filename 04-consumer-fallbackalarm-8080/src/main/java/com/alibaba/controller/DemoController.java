package com.alibaba.controller;

import com.alibaba.model.Depart;
import com.alibaba.service.DepartService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * @Author shenmeng
 * @Date 2020/6/22
 **/
@RestController
@RequestMapping("/consumer/depart")
public class DemoController {

    @Autowired
    private DepartService service;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private ForkJoinPool pool = new ForkJoinPool(5);

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
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "getHystrixHandler")
    @GetMapping("/{id}")
    public Depart getHandler(@PathVariable("id") Integer id, HttpServletRequest request){
        return  service.getDepartById(id);
    }

    /**
     * 服务降级方法 响应给服务端的备选方案
     * @return
     */
    public Depart getHystrixHandler(@PathVariable("id") Integer id,HttpServletRequest request){
        //向管理员发出报警
        String ip = request.getLocalAddr();
        String key = ip+"_getHystrixHandler";
        //指定存放到redis中的key为ip+方法吗
        alarm(key);
        Depart depart = new Depart();
        depart.setId(id);
        depart.setName("no this depart");
        return depart;
    }

    private void alarm(String key) {
        BoundValueOperations<String, String> ops = redisTemplate.boundValueOps(key);
        String value = ops.get();
        if(value ==null){
            synchronized (this){
                value = ops.get();
                if(value ==null){
                    //发送短信
                    sendMsg(key);
                    value = key+"发生了服务降级";
                    ops.set(value,10, TimeUnit.SECONDS);
                }
            }
        }
    }

    private void sendMsg(String key) {
        pool.submit(()->{
            System.out.println(key+"发生了服务降级");
        });
    }

    @GetMapping
    public List<Depart> listHandler(){
        return service.listDeparts();
    }


}
