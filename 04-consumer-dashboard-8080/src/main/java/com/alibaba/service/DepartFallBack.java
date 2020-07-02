package com.alibaba.service;

import com.alibaba.model.Depart;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author shenmeng
 * @createTime 2020/6/30
 */
@Component
@RequestMapping("/fallback/consumer/depart")
public class DepartFallBack implements DepartService {
    @Override
    public boolean saveDepart(Depart depart) {
        System.out.println("对saveDepart方法执行服务降级");
        return false;
    }

    @Override
    public boolean deleteDepartById(Integer id) {
        System.out.println("对deleteDepartById方法执行服务降级");
        return false;
    }

    @Override
    public boolean updateDepart(Depart depart) {
        System.out.println("对updateDepart方法执行服务降级");
        return false;
    }

    @Override
    public Depart getDepartById(Integer id) {
        System.out.println("对getDepartById方法执行服务降级");
        Depart depart = new Depart();
        depart.setId(id);
        depart.setName("no this depart -- class");
        return depart;
    }

    @Override
    public List<Depart> listDeparts() {
        System.out.println("对listDeparts方法执行服务降级");
        return null;
    }
}
