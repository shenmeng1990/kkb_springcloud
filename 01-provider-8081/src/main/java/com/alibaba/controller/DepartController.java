package com.alibaba.controller;

import com.alibaba.model.Depart;
import com.alibaba.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author shenmeng
 * @Date 2020/6/22
 **/
@RestController
@RequestMapping("/depart")
public class DepartController {

    @Autowired
    private DepartService service;

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

}
