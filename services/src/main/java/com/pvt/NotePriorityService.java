package com.pvt;

import com.pvt.beans.NotePriority;
import com.pvt.dao.NotePriorityDAO;
import com.pvt.dao.exceptions.DaoException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by sssergey83 on 16.01.2017.
 */

public class NotePriorityService extends Service<NotePriority> {
    private static final Logger LOG = LoggerFactory.getLogger(NotePriorityService.class);
    private static NotePriorityDAO dao = NotePriorityDAO.getDao();

    private static NotePriorityService service = null;
    Transaction t = null;

    public static synchronized NotePriorityService getService() {
        if (service == null) {
            service = new NotePriorityService();
        }
        return service;
    }

    @Override
    public NotePriority saveOrUpdate(NotePriority notePriority) {
        try {
            Session session = util.getSession();
            t = session.beginTransaction();
            dao.saveOrUpdate(notePriority);
            LOG.info("SaveOrUpdate notePriorityService commit:" + notePriority);
            t.commit();
            session.flush();
        } catch (DaoException e) {
            LOG.error("Error notePriorityService SaveOrUpdate:" + notePriority, e);
            t.rollback();
            return null;
        }
        return notePriority;
    }

    @Override
    public NotePriority get(Serializable id) {
        NotePriority notePriority;
        try {
            Session session = util.getSession();
            t = session.beginTransaction();
            notePriority = dao.get(id);
            LOG.info("Get notePriority commit:" + notePriority);
            t.commit();
            session.flush();
        } catch (DaoException e) {
            LOG.error("Error get notePriority commit" + e.getMessage(), e);
            t.rollback();
            return null;
        }
        return notePriority;
    }

    @Override
    public NotePriority find(String hql) {
        Session session = util.getSession();
        t = session.beginTransaction();
        try {
            NotePriority priorities = dao.find(hql);
            LOG.info("NotePriority find() commit.");
            t.commit();
            session.flush();
            return priorities;
        } catch (DaoException e) {
            LOG.error("Error NotePriority find() commit." + e.getMessage(), e);
            t.rollback();
            return null;
        }
    }

    @Override
    public Set<NotePriority> getAll() {
        try {
            Session session = util.getSession();
            t = session.beginTransaction();
            Set<NotePriority> priorities = dao.getAll();
            LOG.info("NotePriority find() commit.");
            t.commit();
            session.flush();
            return priorities;
        } catch (DaoException e) {
            LOG.error("Error NotePriority find() commit." + e.getMessage(), e);
            t.rollback();
            return null;
        }
    }
}
