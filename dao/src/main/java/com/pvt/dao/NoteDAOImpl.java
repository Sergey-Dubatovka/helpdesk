package com.pvt.dao;

import com.pvt.beans.Note;
import com.pvt.dao.exceptions.DaoException;
import com.pvt.dao.interfaces.INoteDAO;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sssergey83 on 05.02.2017.
 */
@Repository
public class NoteDAOImpl extends BaseDaoImpl<Note> implements INoteDAO {
    private static final Logger LOG = LoggerFactory.getLogger(NoteDAOImpl.class);

    @Autowired
    public NoteDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Note find(String where) throws DaoException {
        try {
            String hql = "FROM Note N WHERE N.subject=:subject";
            Query query = getSession().createQuery(hql);
            query.setParameter("subject", where);
            query.setCacheable(true);
            Note note = (Note) query.uniqueResult();
            return note;
        } catch (HibernateException e) {
            LOG.error("Error find(): " + e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<Note> findUserNotes(Long where) throws DaoException {
        try {
            String hql = "FROM Note N WHERE N.user.userId=:user";
            Query query = getSession().createQuery(hql);
            query.setParameter("user", where);
            query.setCacheable(true);
            List<Note> notes = query.list();
            return notes;
        } catch (HibernateException e) {
            LOG.error("Error find(): " + e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<Note> getAllOpen() throws DaoException {
        try {
            String hql = "FROM Note N WHERE N.noteStatus.statusId<3L order by N.date DESC";
            Query query = getSession().createQuery(hql);
            query.setCacheable(true);
            List<Note> list = query.list();
            return list;
        } catch (HibernateException e) {
            LOG.error("Error find(): " + e);
            throw new DaoException(e);
        }
    }

    @Override
    public Long countAll() throws DaoException {
        Long result;
        try {
            Query query = getSession().createQuery("select count(*) from Note");

            result = (Long) query.uniqueResult();
        } catch (HibernateException he) {
            LOG.error("Error countAllNote");
            throw new DaoException(he);
        }
        return result;
    }

    @Override
    public Long countAllOpen() throws DaoException {
        Long result;
        try {
            Query query = getSession().createQuery("select count(*) from Note where noteStatus.statusId!=3L");
            result = (Long) query.uniqueResult();

        } catch (HibernateException he) {
            LOG.error("Error countAllNote");
            throw new DaoException(he);
        }
        return result;
    }

    @Override
    public Long countInProgress() throws DaoException {
        Long result;
        try {
            Query query = getSession().createQuery("select count(*) from Note where noteStatus.statusId=2L");
            result = (Long) query.uniqueResult();

        } catch (HibernateException he) {
            LOG.error("Error countAllNote");
            throw new DaoException(he);
        }
        return result;
    }

    @Override
    public Long countResolved() throws DaoException {
        Long result;
        try {
            Query query = getSession().createQuery("select count(*) from Note where noteStatus.statusId=3L");
            result = (Long) query.uniqueResult();

        } catch (HibernateException he) {
            LOG.error("Error countAllNote");
            throw new DaoException(he);
        }
        return result;
    }

    @Override
    public List<Note> getPage(int pageIndex, int numberOfRecordsPerPage) throws DaoException {

        Criteria criteria = getSession().createCriteria(Note.class);
        criteria.add(Restrictions.lt("noteStatus.statusId", 3L));
        criteria.addOrder(Order.desc("id"));
        criteria.setFirstResult(pageIndex * numberOfRecordsPerPage - numberOfRecordsPerPage);
        criteria.setMaxResults(numberOfRecordsPerPage);
        criteria.setCacheable(true);

        return criteria.list();
    }
}
