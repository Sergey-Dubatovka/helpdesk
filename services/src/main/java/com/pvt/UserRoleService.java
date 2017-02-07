package com.pvt;

import com.pvt.beans.UserRole;
import com.pvt.dao.UserRoleDAO;
import com.pvt.dao.exceptions.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.Serializable;
import java.util.List;

/**
 * Created by sssergey83 on 16.01.2017.
 */
public class UserRoleService implements IService<UserRole> {
// сделай синглтоны
    private static final Logger LOG = LoggerFactory.getLogger(UserRoleService.class);
    private static UserRoleDAO dao = UserRoleDAO.getDao();

    @Override
    public UserRole saveOrUpdate(UserRole userRole) {
        try {
            util.beginTransaction();
            dao.saveOrUpdate(userRole);
            LOG.info("SaveOrUpdate commit:" + userRole);
            util.commit();
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            util.rollback();
            return null;
        }
        return userRole;
    }

    @Override
    public UserRole get(Serializable id) {
        UserRole userRole;
        try {
            util.beginTransaction();
            userRole = dao.get(id);
            LOG.info("Get commit:" + userRole);
            util.commit();
        } catch (DaoException e) {
            LOG.error("Error get commit:" + e.getMessage(), e);
            util.rollback();
            return null;
        }
        return userRole;
    }

    @Override
    public UserRole load(Serializable id) {
        UserRole user = null;
        try {
            util.beginTransaction();
            user = dao.load(id);
            LOG.info("Load commit:" + user);
            util.commit();
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            util.rollback();
            return null;
        }
        return user;
    }

    @Override
    public boolean delete(UserRole userRole) {
        try {
            util.beginTransaction();
            dao.delete(userRole);
            LOG.info("Delete commit:" + userRole);
            util.commit();
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            util.rollback();
            return false;
        }
        return true;
    }

    @Override
    public List<UserRole> find(String hql) {
        try {
            util.beginTransaction();
            List<UserRole> roles = dao.find(hql);
            LOG.info("UserRole find() commit.");
            util.commit();
            return roles;
        } catch (DaoException e) {
            LOG.error("Error UserRole find() commit." + e.getMessage(), e);
            util.rollback();
            return null;
        }
    }

}
