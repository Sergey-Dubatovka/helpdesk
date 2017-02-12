package com.pvt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by sssergey83 on 11.02.2017.
 */
public class Service<T> implements IService<T> {
    private static final Logger LOG = LoggerFactory.getLogger(Service.class);

    @Override
    public T saveOrUpdate(T t) {
        return null;
    }

    @Override
    public T get(Serializable id) {
        return null;
    }

    @Override
    public T load(Serializable id) {
        return null;
    }

    @Override
    public boolean delete(T t) {
        return false;
    }

    @Override
    public T find(String where) {
        return null;
    }

    @Override
    public Set<T> getAll() {
        return null;
    }
}
