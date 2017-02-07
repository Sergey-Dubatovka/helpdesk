package com.pvt.dao;

import com.pvt.beans.NoteStatus;
import com.pvt.dao.exceptions.DaoException;
import org.hibernate.HibernateException;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by sssergey83 on 05.02.2017.
 */
public class NoteStatusDAO extends BaseDao<NoteStatus> {
    private static final Logger LOG = LoggerFactory.getLogger(NoteStatusDAO.class);

    private static NoteStatusDAO dao = null;

    public static synchronized NoteStatusDAO getDao() {
        if (dao == null) {
            dao = new NoteStatusDAO();
        }
        return dao;
    }
    @Override
    public List<NoteStatus> find(String where) throws DaoException {
        try {
            String hql = "FROM NoteStatus NS WHERE NS.statusName=:statusName";
            Query query = util.getSession().createQuery(hql);
            query.setParameter("statusName", where);
            List<NoteStatus> statuses = query.list();
            return statuses;
        } catch (HibernateException e) {
            LOG.error("Error find(): " + e);
            throw new DaoException(e);
        }
    }
}
