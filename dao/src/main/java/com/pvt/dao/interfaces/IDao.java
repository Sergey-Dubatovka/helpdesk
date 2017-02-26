package com.pvt.dao.interfaces;

import com.pvt.dao.exceptions.DaoException;

import java.io.Serializable;
import java.util.List;

public interface IDao<T> {

    T saveOrUpdate(T t) throws DaoException;

    T get(Class clazz, Serializable id) throws DaoException;

    boolean delete(T t) throws DaoException;

    T find(String where) throws DaoException;

    List<T> getAll(Class clazz) throws DaoException;
}
