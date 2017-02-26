package com.pvt.dao;

import com.pvt.dao.exceptions.DaoException;
import com.pvt.dao.interfaces.IDao;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Repository
public class BaseDaoImpl<T> implements IDao<T> {
    private static final Logger LOG = LoggerFactory.getLogger(BaseDaoImpl.class);
    private SessionFactory sessionFactory;
    Session session;


    @Autowired
    public BaseDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    Session getSession() {
        if (session == null) {
            return sessionFactory.openSession();
        } else {
            return sessionFactory.getCurrentSession();
        }
    }

    @Override
    public T saveOrUpdate(T t) throws DaoException {
        try {
            getSession().saveOrUpdate(t);
            LOG.info("saveOrUpdate(t):" + t);

        } catch (HibernateException e) {
            LOG.error("Error save or update in Dao" + e);
            throw new DaoException(e);
        }
        return t;
    }

    @Override
    public T get(Class clazz, Serializable id) throws DaoException {
        LOG.info("Get class by id:" + id);
        T t;
        try {
            t = (T) getSession().get(clazz, id);
            LOG.info("get clazz:" + t);
        } catch (HibernateException e) {
            LOG.error("Error get " + getPersistentClass() + "in Dao" + e);
            throw new DaoException(e);
        }
        return t;
    }


    @Override
    public boolean delete(T t) throws DaoException {
        try {
            getSession().delete(t);
            LOG.info("Delete:" + t);
            return true;
        } catch (HibernateException e) {
            LOG.error("Error delete():" + e);
            throw new DaoException(e);
        }
    }

    @Override
    public T find(String where) throws DaoException {
        return null;
    }

    @Override
    public List<T> getAll(Class entity) throws DaoException {
        try {
            String hql = "FROM " + entity.getSimpleName();
            Query query = getSession().createQuery(hql);
            query.setCacheable(true);
            List<T> t = query.list();
            return t;
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.error("Error find(): " + e);
            throw new DaoException(e);
        }
    }

    private Class getPersistentClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
