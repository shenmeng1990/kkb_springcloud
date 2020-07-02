package com.alibaba.service;

import com.alibaba.model.Depart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//指定当前接口为feign客户端，参数为提供者的微服务名称
@FeignClient(value = "depart-provider",fallback = DepartFallBack.class)
@RequestMapping("/provider/depart")
public interface DepartService {

    @PostMapping
    boolean saveDepart(@RequestBody Depart depart);

    @DeleteMapping("/{id}")
    boolean deleteDepartById(@PathVariable("id") Integer id);

    @PutMapping
    boolean updateDepart(@RequestBody Depart depart);

    @GetMapping("/{id}")
    Depart getDepartById(@PathVariable("id") Integer id);

    @GetMapping
    List<Depart> listDeparts();

}
