package com.alibaba.service.impl;

import com.alibaba.repository.DepartRepository;
import com.alibaba.model.Depart;
import com.alibaba.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @Author shenmeng
 * @Date 2020/6/22
 **/
@Service
public class DepartServiceImpl implements DepartService {

    @Autowired
    private DepartRepository repository;

    @Value("${server.port}")
    private int port;



    @Override
    public boolean saveDepart(Depart depart) {
        //对于save操作的参数，根据id不同有三种不同的情况
        //depart 的id为null 插入操作
        //depart 的id不为null,但db中存在 更新操作
        //depart 的id不为null，但db中不存在 插入操作，数据库自动生成新id
        Depart save = repository.save(depart);
        Optional<Depart> optionalDepart = Optional.of(save);
        return optionalDepart.isPresent();
    }

    @Override
    public boolean deleteDepartById(Integer id) {

        //如果在db中指定的id若不成长，则该方法抛出异常
        boolean isExist = repository.existsById(id);
        if (isExist){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateDepart(Depart depart) {
        Depart save = repository.save(depart);
        Optional<Depart> optionalDepart = Optional.of(save);
        return optionalDepart.isPresent();
    }

    @Override
    public Depart getDepartById(Integer id) {
       /* try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        boolean isExist = repository.existsById(id);
        if(isExist){
            Depart depart = repository.getOne(id);
            depart.setName(depart.getName()+port);
            return depart;
        }
        Depart depart = new Depart();
        depart.setId(id);
        depart.setName("no this depart");
        return depart;
    }

    @Override
    public List<Depart> listDeparts() {
        List<Depart> departList = repository.findAll();
        if(!CollectionUtils.isEmpty(departList)){
            for (Depart depart : departList) {
                depart.setName(depart.getName()+port);
            }
        }
        return departList;
    }
}
