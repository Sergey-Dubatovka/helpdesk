package com.pvt;

import com.pvt.beans.UserRole;
import com.pvt.dao.UserRoleDAO;
import com.pvt.dao.exceptions.DaoException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by sssergey83 on 16.01.2017.
 */
public class UserRoleService extends Service<UserRole> {

    private static final Logger LOG = LoggerFactory.getLogger(UserRoleService.class);
    private static UserRoleDAO dao = UserRoleDAO.getDao();
    Transaction t = null;


    private static UserRoleService service = null;

    public static synchronized UserRoleService getService() {
        if (service == null) {
            service = new UserRoleService();
        }
        return service;
    }

    @Override
    public UserRole saveOrUpdate(UserRole userRole) {
        try {
            Session session = util.getSession();
            t = session.beginTransaction();
            dao.saveOrUpdate(userRole);
            LOG.info("SaveOrUpdate commit:" + userRole);
            t.commit();
            session.flush();
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            t.rollback();
            return null;
        }
        return userRole;
    }

    @Override
    public UserRole get(Serializable id) {
        UserRole userRole;
        try {
            Session session = util.getSession();
            t = session.beginTransaction();
            userRole = dao.get(id);
            LOG.info("Get commit:" + userRole);
            t.commit();
            session.flush();
        } catch (DaoException e) {
            LOG.error("Error get commit:" + e.getMessage(), e);
            t.rollback();
            return null;
        }
        return userRole;
    }

    @Override
    public boolean delete(UserRole userRole) {
        try {
            Session session = util.getSession();
            t = session.beginTransaction();
            dao.delete(userRole);
            LOG.info("Delete commit:" + userRole);
            t.commit();
            session.flush();
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            t.rollback();
            return false;
        }
        return true;
    }

    @Override
    public UserRole find(String hql) {
        try {
            Session session = util.getSession();
            t = session.beginTransaction();
            UserRole role = dao.find(hql);
            LOG.info("UserRole find() commit.");
            t.commit();
            session.flush();
            return role;
        } catch (DaoException e) {
            LOG.error("Error UserRole find() commit." + e.getMessage(), e);
            t.rollback();
            return null;
        }
    }

    @Override
    public Set<UserRole> getAll() {
        try {
            Session session = util.getSession();
            t = session.beginTransaction();
            Set<UserRole> userSet = dao.getAll("UserRole");
            LOG.info("userRole find() commit.");
            t.commit();
            session.flush();
            return userSet;
        } catch (DaoException e) {
            LOG.error("Error userRole find() commit." + e.getMessage(), e);
            t.rollback();
            return null;
        }
    }
}
