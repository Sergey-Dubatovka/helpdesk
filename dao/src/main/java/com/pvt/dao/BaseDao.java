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
public class BaseDao<T> implements IDao<T> {
    private static final Logger LOG = LoggerFactory.getLogger(BaseDao.class);
    private SessionFactory sessionFactory;
    private Session session;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    public BaseDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.session = sessionFactory.openSession();
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public T saveOrUpdate(T t) throws DaoException {
        try {
            getSession().update(t);
            LOG.info("saveOrUpdate(t):" + t);
        } catch (HibernateException e) {
            LOG.error("Error save or update in Dao" + e);
            throw new DaoException(e);
        }
        return t;
    }

    @Override
    public T get(Serializable id) throws DaoException {
        LOG.info("Get class by id:" + id);
        T t;
        try {
            t = (T) getSession().get(getPersistentClass(), id);
            LOG.info("get clazz:" + t);
        } catch (HibernateException e) {
            LOG.error("Error get " + getPersistentClass() + "in Dao" + e);
            throw new DaoException(e);
        }
        return t;
    }

    @Override
    public T load(Serializable id) throws DaoException {
        LOG.info("Load class by id:" + id);
        T t;
        try {
            t = (T) getSession().load(getPersistentClass(), id);
            LOG.info("load() clazz:" + t);
        } catch (HibernateException e) {
            LOG.error("Error load():" + getPersistentClass() + " in Dao " + e);
            throw new DaoException(e);
        }
        return t;
    }

    @Override
    public boolean delete(T t) throws DaoException {
        try {
            getSession().delete(t);
            LOG.info("Delete:" + t);
        } catch (HibernateException e) {
            LOG.error("Error delete():" + e);
            throw new DaoException(e);
        }
        return true;
    }

    @Override
    public T find(String where) throws DaoException {
        return null;
    }

    @Override
    public List<T> getAll(String entity) throws DaoException {
        try {
            String hql = "FROM " + entity;
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
