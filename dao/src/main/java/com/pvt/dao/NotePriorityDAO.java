package com.pvt.dao;

import com.pvt.beans.GamingClub;
import com.pvt.beans.NotePriority;
import com.pvt.dao.exceptions.DaoException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sssergey83 on 05.02.2017.
 */
public class NotePriorityDAO extends BaseDao<NotePriority> {
    private static final Logger LOG = LoggerFactory.getLogger(NotePriorityDAO.class);

    private static NotePriorityDAO dao = null;

    public static synchronized NotePriorityDAO getDao() {
        if (dao == null) {
            dao = new NotePriorityDAO();
        }
        return dao;
    }
    @Override
    public List <NotePriority> find(String where) throws DaoException {
        try {
            String hql = "FROM NotePriority NP WHERE NP.priorityName=:priorityName";
            Query query = util.getSession().createQuery(hql);
            query.setParameter("priorityName", where);
            List<NotePriority> priorities = query.list();
            return priorities;
        } catch (HibernateException e) {
            LOG.error("Error find(): " + e);
            throw new DaoException(e);
        }
    }
    public Set<NotePriority> getAll() throws DaoException {
        try {
            String hql = "FROM NotePriority";
            Query query = util.getSession().createQuery(hql);
            List<NotePriority> list = query.list();
            Set<NotePriority> priorities = new HashSet<>();
            priorities.addAll(list);
            return priorities;
        } catch (HibernateException e) {
            LOG.error("Error find(): " + e);
            throw new DaoException(e);
        }
    }
}
