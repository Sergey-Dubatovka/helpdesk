package com.pvt.dao;

import com.pvt.beans.Note;
import com.pvt.beans.NoteStatus;
import com.pvt.beans.User;
import com.pvt.dao.exceptions.DaoException;

import org.hibernate.HibernateException;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

/**
 * Created by sssergey83 on 04.02.2017.
 */
public class UserDAO extends BaseDao<User> {
    private static final Logger LOG = LoggerFactory.getLogger(UserDAO.class);

    private static UserDAO dao = null;

    public static synchronized UserDAO getDao() {
        if (dao == null) {
            dao = new UserDAO();
        }
        return dao;
    }

    @Override
    public User find(String login) throws DaoException {
        try {
            String hql = "FROM User U WHERE U.login=:login";
            Query query = util.getSession().createQuery(hql);
            query.setParameter("login", login);
            List<User> users = query.list();
            return users.get(0);
        } catch (HibernateException e) {
            LOG.error("Error find(): " + e);
            throw new DaoException(e);
        }
    }

    //public Set<Note> getNotes()
}
