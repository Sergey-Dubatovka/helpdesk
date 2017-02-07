package com.pvt.dao;

import com.pvt.beans.Note;
import com.pvt.dao.exceptions.DaoException;
import org.hibernate.HibernateException;

import org.hibernate.Query;
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
    public List<Note> find(String where) throws DaoException {
        try {
            String hql = "FROM Note N WHERE N.subject=:subject";
            Query query = util.getSession().createQuery(hql);
            query.setParameter("subject", where);
            List<Note> notes = query.list();
            return notes;
        } catch (HibernateException e) {
            LOG.error("Error find(): " + e);
            throw new DaoException(e);
        }
    }
}
