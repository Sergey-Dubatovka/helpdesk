package com.pvt;

import com.pvt.beans.NotePriority;
import com.pvt.dao.NotePriorityDAO;
import com.pvt.dao.exceptions.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.Serializable;
import java.util.List;

/**
 * Created by sssergey83 on 16.01.2017.
 */

public class NotePriorityService implements IService<NotePriority> {
    private static final Logger LOG = LoggerFactory.getLogger(NotePriorityService.class);
    private static NotePriorityDAO dao =  NotePriorityDAO.getDao();

    @Override
    public NotePriority saveOrUpdate(NotePriority notePriority) {
        try {
            util.beginTransaction();
            dao.saveOrUpdate(notePriority);
            LOG.info("SaveOrUpdate notePriorityService commit:" + notePriority);
            util.commit();
        } catch (DaoException e) {
            LOG.error("Error notePriorityService SaveOrUpdate:" + notePriority, e);
            util.rollback();
            return null;
        }
        return notePriority;
    }

    @Override
    public NotePriority get(Serializable id) {
        NotePriority notePriority;
        try {
            util.beginTransaction();
            notePriority = dao.get(id);
            LOG.info("Get notePriority commit:" + notePriority);
            util.commit();
        } catch (DaoException e) {
            LOG.error("Error get notePriority commit" + e.getMessage(), e);
            util.rollback();
            return null;
        }
        return notePriority;
    }

    @Override
    public NotePriority load(Serializable id) {
        NotePriority notePriority = null;
        try {
            util.beginTransaction();
            notePriority = dao.load(id);
        } catch (DaoException e) {
            LOG.error("Error load notePriority commit" + e.getMessage(), e);
            util.rollback();
            return null;
        }
        return notePriority;
    }

    @Override
    public boolean delete(NotePriority notePriority) {
        try {
            util.beginTransaction();
            dao.delete(notePriority);
            LOG.info("Delete notePriority commit:" + notePriority);
           util.commit();
        } catch (DaoException e) {
            util.rollback();
            LOG.error("Error delete notePriority commit:" + e.getMessage(), e);
            return false;
        }
        return true;
    }

    @Override
    public List<NotePriority> find(String hql) {
        try {
            util.beginTransaction();
            List<NotePriority> priorities = dao.find(hql);
            LOG.info("NotePriority find() commit.");
            util.commit();
            return priorities;
        } catch (DaoException e) {
            LOG.error("Error NotePriority find() commit." + e.getMessage(), e);
            util.rollback();
            return null;
        }
    }
}
