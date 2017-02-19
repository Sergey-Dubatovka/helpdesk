package com.pvt.dao;

import com.pvt.beans.Note;
import com.pvt.beans.NoteStatus;
import com.pvt.beans.User;
import com.pvt.dao.exceptions.DaoException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;

import org.hibernate.Query;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
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

    public Long allUserCount() throws DaoException {
        Long result;
        try {
            Criteria criteria = util.getSession().createCriteria(User.class);
            criteria.setProjection(Projections.rowCount());
            result = (Long) criteria.uniqueResult();

        } catch (HibernateException he) {
            LOG.error("Error in allUserCount" + he.getMessage());
            throw new DaoException(he);
        }
        return result;
    }

    public Long countUserByRole(String role) throws DaoException {
        Long idRole;
        switch (role) {
            case "Manager":
                idRole = 1L;
                break;
            case "Worker":
                idRole = 2L;
                break;
            case "Director":
                idRole = 3L;
                break;
            default:
                idRole = null;
                break;
        }
        try {
            Query query = util.getSession().createQuery("select count (*) from User where userRole.roleId=:id");
            query.setParameter("id", idRole);
            return (Long) query.uniqueResult();
        } catch (HibernateException he) {
            LOG.error("Error in allUserCount" + he.getMessage());
            throw new DaoException(he);
        }
    }
}
