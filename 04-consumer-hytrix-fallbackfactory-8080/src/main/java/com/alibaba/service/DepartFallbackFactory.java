package com.alibaba.service;

import com.alibaba.model.Depart;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author shenmeng
 * @createTime 2020/6/30
 */
@Component
public class DepartFallbackFactory implements FallbackFactory<DepartService> {
    @Override
    public DepartService create(Throwable throwable) {
        return new DepartService() {
            @Override
            public boolean saveDepart(Depart depart) {
                System.out.println("对saveDepart方法服务降级");
                return false;
            }

            @Override
            public boolean deleteDepartById(Integer id) {
                System.out.println("对deleteDepartById方法服务降级");
                return false;
            }

            @Override
            public boolean updateDepart(Depart depart) {
                System.out.println("对updateDepart方法服务降级");
                return false;
            }

            @Override
            public Depart getDepartById(Integer id) {
                Depart depart = new Depart();
                depart.setId(id);
                depart.setName("no this depart -- class");
                return depart;
            }

            @Override
            public List<Depart> listDeparts() {
                System.out.println("对listDeparts方法服务降级");
                return null;
            }
        };
    }
}
