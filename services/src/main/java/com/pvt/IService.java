package com.pvt;

import java.util.List;

/**
 * Created by sssergey83 on 16.01.2017.
 */
public interface IService <T> {
    T read(int id);

    /**
     *
     * @param entity
     * @return
     */
    boolean create(T entity);

    /**
     *
     * @param entity
     * @return
     */
    boolean update(T entity);

    /**
     *
     * @param entity
     * @return
     */
    boolean delete(T entity);

    /**
     *
     * @param WhereAndOrder
     * @return
     */
    List<T> getAll(String WhereAndOrder);
}
