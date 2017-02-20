package com.pvt.dao;

import com.pvt.beans.Note;
import com.pvt.dao.exceptions.DaoException;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by sssergey83 on 05.02.2017.
 */
public class NoteDAO extends BaseDao<Note> {
    private static final Logger LOG = LoggerFactory.getLogger(NoteDAO.class);

    private static NoteDAO dao = null;

    public static synchronized NoteDAO getDao() {
        if (dao == null) {
            dao = new NoteDAO();
        }
        return dao;
    }

    @Override
    public Note find(String where) throws DaoException {
        try {
            String hql = "FROM Note N WHERE N.subject=:subject";
            Query query = util.getSession().createQuery(hql);
            query.setParameter("subject", where);
            query.setCacheable(true);
            List<Note> notes = query.list();
            return notes.get(0);
        } catch (HibernateException e) {
            LOG.error("Error find(): " + e);
            throw new DaoException(e);
        }
    }

    public List<Note> findUserNotes(Long where) throws DaoException {
        try {
            String hql = "FROM Note N WHERE N.user.userId=:user";
            Query query = util.getSession().createQuery(hql);
            query.setParameter("user", where);
            query.setCacheable(true);
            List<Note> notes = query.list();
            return notes;
        } catch (HibernateException e) {
            LOG.error("Error find(): " + e);
            throw new DaoException(e);
        }
    }

    public List<Note> getAllOpen() throws DaoException {
        try {
            String hql = "FROM Note N WHERE N.noteStatus.statusId<3L order by N.date DESC";
            Query query = util.getSession().createQuery(hql);
            query.setCacheable(true);
            List<Note> list = query.list();
            return list;
        } catch (HibernateException e) {
            LOG.error("Error find(): " + e);
            throw new DaoException(e);
        }
    }

    public Long countAll() throws DaoException {
        Long result;
        try {
            Query query = util.getSession().createQuery("select count(*) from Note");
            result = (Long) query.uniqueResult();

        } catch (HibernateException he) {
            LOG.error("Error countAllNote");
            throw new DaoException(he);
        }
        return result;
    }

    public Long countOpen() throws DaoException {
        Long result;
        try {
            Query query = util.getSession().createQuery("select count(*) from Note where noteStatus.statusId!=3L");
            result = (Long) query.uniqueResult();

        } catch (HibernateException he) {
            LOG.error("Error countAllNote");
            throw new DaoException(he);
        }
        return result;
    }

    public Long countInProgress() throws DaoException {
        Long result;
        try {
            Query query = util.getSession().createQuery("select count(*) from Note where noteStatus.statusId=2L");
            result = (Long) query.uniqueResult();

        } catch (HibernateException he) {
            LOG.error("Error countAllNote");
            throw new DaoException(he);
        }
        return result;
    }

    public Long countResolved() throws DaoException {
        Long result;
        try {
            Query query = util.getSession().createQuery("select count(*) from Note where noteStatus.statusId=3L");
            result = (Long) query.uniqueResult();

        } catch (HibernateException he) {
            LOG.error("Error countAllNote");
            throw new DaoException(he);
        }
        return result;
    }

    public List<Note> getPage(int pageIndex, int numberOfRecordsPerPage) throws DaoException {

        Criteria criteria = util.getSession().createCriteria(Note.class);
        criteria.add(Restrictions.lt("noteStatus.statusId", 3L));
        criteria.addOrder(Order.desc("id"));
        criteria.setFirstResult(pageIndex * numberOfRecordsPerPage - numberOfRecordsPerPage);
        criteria.setMaxResults(numberOfRecordsPerPage);
        criteria.setCacheable(true);

        return criteria.list();
    }
}
