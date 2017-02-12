package com.pvt;

import com.pvt.dao.util.HibernateUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by sssergey83 on 16.01.2017.
 */
public interface IService<T> {

    HibernateUtil util = HibernateUtil.getHibernateUtil();

    T saveOrUpdate(T t);

    T get(Serializable id);

    T load(Serializable id);

    boolean delete(T t);

    T find(String where);

    Set<T> getAll();
}
