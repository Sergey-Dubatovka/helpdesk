package com.pvt;

import com.pvt.beans.User;
import com.pvt.dao.UserDAO;
import com.pvt.dao.exceptions.DaoException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by sssergey83 on 16.01.2017.
 */
public class UserService extends Service<User> {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    private static UserDAO dao = UserDAO.getDao();
    private static UserService service = null;
    Transaction t = null;

    public static synchronized UserService getService() {
        if (service == null) {
            service = new UserService();
        }
        return service;
    }

    @Override
    public User saveOrUpdate(User user) {
        try {
            Session session = util.getSession();
            t = session.beginTransaction();
            dao.saveOrUpdate(user);
            LOG.info("SaveOrUpdate commit:" + user);
            t.commit();
            session.flush();
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            t.rollback();
            return null;
        }
        return user;
    }

    @Override
    public User get(Serializable id) {
        User user;
        try {
            Session session = util.getSession();
            t = session.beginTransaction();
            user = dao.get(id);
            LOG.info("Get commit:" + user);
            t.commit();
            session.flush();
        } catch (DaoException e) {
            LOG.error("Error get commit:" + e.getMessage(), e);
            t.rollback();
            return null;
        }
        return user;
    }

    @Override
    public User load(Serializable id) {
        User user;
        try {
            Session session = util.getSession();
            t = session.beginTransaction();
            user = dao.load(id);
            LOG.info("Load commit:" + user);
            t.commit();
            session.flush();
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            t.rollback();
            return null;
        }
        return user;
    }

    @Override
    public boolean delete(User user) {
        util.getSession();

        try {
            Session session = util.getSession();
            t = session.beginTransaction();
            dao.delete(user);
            LOG.info("Delete commit:" + user);
            t.commit();
            session.flush();
        } catch (DaoException e) {
            LOG.error("Error delete user" + e.getMessage(), e);
            t.rollback();
            return false;
        }
        return true;
    }

    @Override
    public User find(String login) {
        User user = null;
        try {
            Session session = util.getSession();
            t = session.beginTransaction();
            user = dao.find(login);
            t.commit();
            session.flush();
        } catch (DaoException e) {
            t.rollback();
            LOG.error("" + e);
        }
        return user;
    }

    @Override
    public Set<User> getAll() {
        Set<User> userSet;
        try {
            Session session = util.getSession();
            t = session.beginTransaction();
            userSet = dao.getAll("User");
            LOG.info("NoteStatus find() commit.");
            t.commit();
            session.flush();
            return userSet;
        } catch (DaoException e) {
            LOG.error("Error NotePriority find() commit." + e.getMessage(), e);
            t.rollback();
            return null;
        }
    }
}
