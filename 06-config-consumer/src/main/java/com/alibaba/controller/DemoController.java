package com.alibaba.controller;

import com.alibaba.model.Depart;
import com.alibaba.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${prefix}")
    private String prefix;

    @PostMapping
    public boolean saveHandler(@RequestBody Depart depart){
        depart.setName(prefix+depart.getName());
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

    @GetMapping("/{id}")
    public Depart getHandler(@PathVariable("id") Integer id){
        return  service.getDepartById(id);
    }

    @GetMapping
    public List<Depart> listHandler(){
        return service.listDeparts();
    }


}
