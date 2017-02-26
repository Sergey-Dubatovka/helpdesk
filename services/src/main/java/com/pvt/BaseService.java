package com.pvt;

import com.pvt.dao.exceptions.DaoException;
import com.pvt.dao.interfaces.IDao;
import com.pvt.exceptions.ServiceException;
import com.pvt.interfaces.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sssergey83 on 11.02.2017.
 */

@Service
public class BaseService<T> implements IService<T> {
    private static Logger log = LoggerFactory.getLogger(BaseService.class);

    @Autowired
    private IDao<T> baseDao;

    @Override
    public T saveOrUpdate(T t) throws ServiceException {
        try {
            return baseDao.saveOrUpdate(t);
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public T get(Class clazz, Serializable id) throws ServiceException {
        try {
            return (T) baseDao.get(clazz, id);
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(T t) throws ServiceException {
        try {
            return baseDao.delete(t);
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public T find(String where) throws ServiceException {
        T t = null;
        try {
            t = baseDao.find(where);
        } catch (DaoException e) {
            log.error("" + e);
        }
        return t;
    }

    @Override
    public List<T> getAll(Class clazz) throws ServiceException {
        try {
            return baseDao.getAll(clazz);
        } catch (DaoException e) {
            log.error(e.getMessage(), e);
            throw new ServiceException(e);
        }
    }
}
