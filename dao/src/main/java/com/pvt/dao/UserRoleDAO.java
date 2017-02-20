package com.pvt.dao;

import com.pvt.beans.UserRole;
import com.pvt.dao.exceptions.DaoException;
import org.hibernate.HibernateException;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by sssergey83 on 05.02.2017.
 */
public class UserRoleDAO extends BaseDao<UserRole> {
    private static final Logger LOG = LoggerFactory.getLogger(UserRoleDAO.class);
    private static UserRoleDAO dao = null;

    public static synchronized UserRoleDAO getDao() {
        if (dao == null) {
            dao = new UserRoleDAO();
        }
        return dao;
    }

    @Override
    public UserRole find(String where) throws DaoException {
        try {
            String hql = "FROM UserRole UR WHERE UR.roleName=:roleName";
            Query query = util.getSession().createQuery(hql);
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
