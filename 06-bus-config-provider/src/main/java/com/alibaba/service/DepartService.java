package com.alibaba.service;

import com.alibaba.model.Depart;

import java.util.List;

/**
 * @Author shenmeng
 * @Date 2020/6/22
 **/
public interface DepartService {

    boolean saveDepart(Depart depart);

    boolean deleteDepartById(Integer id);

    boolean updateDepart(Depart depart);

    Depart getDepartById(Integer id);

    List<Depart> listDeparts();

}
