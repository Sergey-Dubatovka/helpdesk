package com.pvt.interfaces;

import com.pvt.exceptions.ServiceException;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sssergey83 on 16.01.2017.
 */
public interface IService<T> {
    /**
     * Save or update entity <T>
     *
     * @param t - Object to save or update
     * @return saved or updated object
     * @throws ServiceException
     */
    T saveOrUpdate(T t) throws ServiceException;

    /**
     * Return entity by id
     *
     * @param id - id in DataBase
     * @return entity
     * @throws ServiceException
     */
    T get(Class clazz, Serializable id) throws ServiceException;

    /**
     * Delete persistent entity
     *
     * @param t - entity
     * @return true - if delete was successful
     * @throws ServiceException
     */
    boolean delete(T t) throws ServiceException;

    /**
     * Find entity by String parameter (where):
     * User         where = login
     * UserRole     where = roleName
     * Note         where = subject
     * NotePriority where = priorityName
     * NoteStatus   where = priorityStatus
     *
     * @param where - String parameter
     * @return entity
     * @throws ServiceException
     */
    T find(String where) throws ServiceException;

    /**
     * Get Set<T> of all entity T
     *
     * @return Set
     * @throws ServiceException
     */
    List<T> getAll(Class clazz) throws ServiceException;
}
