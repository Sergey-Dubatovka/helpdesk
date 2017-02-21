package com.pvt.dao;

import com.pvt.dao.exceptions.DaoException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class BaseDao<T> implements IDao<T> {
    private static final Logger LOG = LoggerFactory.getLogger(BaseDao.class);

    public BaseDao() {
    }

    @Override
    public T saveOrUpdate(T t) throws DaoException {
        try {
            Session session = util.getSession();
            session.saveOrUpdate(t);
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
            Session session = util.getSession();
            t = (T) session.get(getPersistentClass(), id);
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
            Session session = util.getSession();
            t = (T) session.load(getPersistentClass(), id);
            LOG.info("load() clazz:" + t);
            session.isDirty();
        } catch (HibernateException e) {
            LOG.error("Error load():" + getPersistentClass() + " in Dao " + e);
            throw new DaoException(e);
        }
        return t;
    }

    @Override
    public boolean delete(T t) throws DaoException {
        try {
            Session session = util.getSession();
                        session.delete(t);
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
    public Set<T> getAll(String entity) throws DaoException {
        try {
            String hql = "FROM " + entity;
            Query query = util.getSession().createQuery(hql);
            List<T> list = query.list();
            Set<T> t = new HashSet<>();
            t.addAll(list);
            return t;
        } catch (HibernateException e) {
            LOG.error("Error find(): " + e);
            throw new DaoException(e);
        }
    }

       private Class getPersistentClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
