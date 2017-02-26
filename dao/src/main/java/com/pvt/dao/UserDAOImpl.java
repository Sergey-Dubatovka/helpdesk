package com.pvt.dao;

import com.pvt.beans.User;
import com.pvt.dao.exceptions.DaoException;
import com.pvt.dao.interfaces.IUserDAO;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sssergey83 on 04.02.2017.
 */
@Repository
public class UserDAOImpl extends BaseDaoImpl<User> implements IUserDAO {
    private static final Logger LOG = LoggerFactory.getLogger(UserDAOImpl.class);

    @Autowired
    private UserDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
        public User find(String login) throws DaoException {
        try {
            String hql = "FROM User U WHERE U.login=:login";
            Query query = getSession().createQuery(hql);
            query.setParameter("login", login);
            query.setCacheable(true);
            List<User> users = query.list();
            return users.get(0);
        } catch (HibernateException e) {
            LOG.error("Error find(): " + e);
            throw new DaoException(e);
        }
    }

    public Long countAllUsers() throws DaoException {
        Long result;
        try {
            Criteria criteria = getSession().createCriteria(User.class);
            criteria.setProjection(Projections.rowCount());
            criteria.setCacheable(true);
            result = (Long) criteria.uniqueResult();

        } catch (HibernateException he) {
            LOG.error("Error in countAllUsers" + he.getMessage());
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
            Query query = getSession().createQuery("select count (*) from User where userRole.roleId=:id");
            query.setParameter("id", idRole);
            return (Long) query.uniqueResult();
        } catch (HibernateException he) {
            LOG.error("Error in countAllUsers" + he.getMessage());
            throw new DaoException(he);
        }
    }
}
