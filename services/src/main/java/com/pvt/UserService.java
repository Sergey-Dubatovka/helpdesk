package com.pvt;

import com.pvt.beans.User;
import com.pvt.dao.UserDAO;
import com.pvt.dao.exceptions.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by sssergey83 on 16.01.2017.
 */
public class UserService implements IService<User> {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    private static UserDAO dao = UserDAO.getDao();

    private static UserService service = null;

    public static synchronized UserService getService() {
        if (service == null) {
            service = new UserService();
        }
        return service;
    }

    @Override
    public User saveOrUpdate(User user) {
        try {
            util.getSession();
            util.beginTransaction();
            dao.saveOrUpdate(user);
            LOG.info("SaveOrUpdate commit:" + user);
            util.commit();
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            util.rollback();
            return null;
        }
        return user;
    }

    @Override
    public User get(Serializable id) {
        User user;
        try {
            util.beginTransaction();
            user = dao.get(id);
            LOG.info("Get commit:" + user);
            util.commit();
        } catch (DaoException e) {
            LOG.error("Error get commit:" + e.getMessage(), e);
            util.rollback();
            return null;
        }
        return user;
    }

    @Override
    public User load(Serializable id) {
        User user = null;
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
    public boolean delete(User user) {
        try {
            util.beginTransaction();
            dao.delete(user);
            LOG.info("Delete commit:" + user);
            util.commit();
        } catch (DaoException e) {
            LOG.error("Error delete user" + e.getMessage(), e);
            util.rollback();
            return false;
        }
        return true;
    }

    @Override
    public List<User> find(String login) {
        try {
            util.beginTransaction();
            List<User> users = dao.find(login);
            LOG.info("User find() commit.");
            util.commit();
            return users;
        } catch (DaoException e) {
            LOG.error("Error User find() commit." + e.getMessage(), e);
            util.rollback();
            return null;
        }
    }

    @Override
    public Set<User> getAll() {
        return null;
    }
}
