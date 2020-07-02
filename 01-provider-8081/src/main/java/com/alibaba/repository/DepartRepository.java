package com.alibaba.repository;

import com.alibaba.model.Depart;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author shenmeng
 * @Date 2020/6/22
 **/
//第一个泛型是当前repository操作的类型
//第二个泛型是当前repository操作的对象的id的类型
public interface DepartRepository extends JpaRepository<Depart,Integer>{
}
