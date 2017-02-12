package com.pvt.dao;

import com.pvt.dao.exceptions.DaoException;
import com.pvt.dao.util.HibernateUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public interface IDao<T> {

    HibernateUtil util = HibernateUtil.getHibernateUtil();

    T saveOrUpdate(T t) throws DaoException;

    T get(Serializable id) throws DaoException;

    T load(Serializable id) throws DaoException;

    boolean delete(T t) throws DaoException;

    T find(String where) throws DaoException;

    Set<T> getAll(String entity) throws DaoException;
}
