package com.pvt.dao;

import com.pvt.beans.UserRole;
import com.pvt.dao.exceptions.DaoException;
import com.pvt.dao.interfaces.IUserRoleDAO;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sssergey83 on 05.02.2017.
 */
@Repository
public class UserRoleDAO extends BaseDao<UserRole> implements IUserRoleDAO {
    private static final Logger LOG = LoggerFactory.getLogger(UserRoleDAO.class);

    @Autowired
    public UserRoleDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public UserRole find(String where) throws DaoException {
        try {
            String hql = "FROM UserRole UR WHERE UR.roleName=:roleName";
            Query query = getSession().createQuery(hql);
            query.setParameter("roleName", where);
            query.setCacheable(true);
            List<UserRole> roles = query.list();
            return roles.get(0);
        } catch (HibernateException e) {
            LOG.error("Error find(): " + e);
            throw new DaoException(e);
        }
    }
}
