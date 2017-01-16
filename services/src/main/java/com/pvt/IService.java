package com.pvt;

import java.util.List;

/**
 * Created by sssergey83 on 16.01.2017.
 */
public interface IService <T> {
    T read(int id);

    boolean create(T entity);

    boolean update(T entity);

    boolean delete(T entity);

    List<T> getAll(String WhereAndOrder);
}
