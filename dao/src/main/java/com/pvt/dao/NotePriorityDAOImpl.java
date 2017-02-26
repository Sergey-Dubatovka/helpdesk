package com.pvt.dao;

import com.pvt.beans.NotePriority;
import com.pvt.dao.exceptions.DaoException;
import com.pvt.dao.interfaces.INotePriorityDAO;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on 05.02.2017.
 */
@Repository
public class NotePriorityDAOImpl extends BaseDaoImpl<NotePriority> implements INotePriorityDAO {
    private static final Logger LOG = LoggerFactory.getLogger(NotePriorityDAOImpl.class);

    @Autowired
    private NotePriorityDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public NotePriority find(String where) throws DaoException {
        try {
            String hql = "FROM NotePriority NP WHERE NP.priorityName=:priorityName";
            Query query = getSession().createQuery(hql);
            query.setParameter("priorityName", where);
            query.setCacheable(true);
            List<NotePriority> priorities = query.list();
            return priorities.get(0);
        } catch (HibernateException e) {
            LOG.error("Error find(): " + e);
            throw new DaoException(e);
        }
    }
//    public Set<NotePriority> getAll() throws DaoException {
//        try {
//            String hql = "FROM NotePriority";
//            Query query = util.getSession().createQuery(hql);
//            query.setCacheable(true);
//            List<NotePriority> list = query.list();
//            Set<NotePriority> priorities = new HashSet<>();
//            priorities.addAll(list);
//            return priorities;
//        } catch (HibernateException e) {
//            LOG.error("Error find(): " + e);
//            throw new DaoException(e);
//        }
//    }
}
