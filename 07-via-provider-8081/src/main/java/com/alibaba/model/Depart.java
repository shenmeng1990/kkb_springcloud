package com.alibaba.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Author shenmeng
 * @Date 2020/6/22
 **/
/**
 * HttpMessageConvert Jackson->完成java对象与json数据之间的转换工作
 * JPA的默认实现是Hibernate，而Hibernate默认对于对象的查询是基于延迟加载的
 * Depart depart = service.findDepartById(1); 这里的depart实际是一个javasist动态代理对象
 * String name = depart.getName();
 * */
@Data
@Entity //使用自动建表
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Depart {

    @Id //建表的主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

}
