package com.pvt.dao;

import com.pvt.beans.NoteStatus;
import com.pvt.dao.exceptions.DaoException;
import com.pvt.dao.interfaces.INoteDAO;
import com.pvt.dao.interfaces.INoteStatusDAO;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sssergey83 on 05.02.2017.
 */
@Repository
public class NoteStatusDAO extends BaseDao<NoteStatus> implements INoteStatusDAO {
    private static final Logger LOG = LoggerFactory.getLogger(NoteStatusDAO.class);

    @Autowired
    private NoteStatusDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public NoteStatus find(String where) throws DaoException {
        try {
            String hql = "FROM NoteStatus NS WHERE NS.statusName=:statusName";
            Query query = getSession().createQuery(hql);
            query.setParameter("statusName", where);
            query.setCacheable(true);
            List<NoteStatus> statuses = query.list();
            return statuses.get(0);
        } catch (HibernateException e) {
            LOG.error("Error find(): " + e);
            throw new DaoException(e);
        }
    }
//    public Set<NoteStatus> getAll() throws DaoException {
//        try {
//            String hql = "FROM NoteStatus";
//            Query query = util.getSession().createQuery(hql);
//            query.setCacheable(true);
//            List<NoteStatus> list = query.list();
//            Set<NoteStatus> statuses = new HashSet<>();
//            statuses.addAll(list);
//            return statuses;
//        } catch (HibernateException e) {
//            LOG.error("Error find(): " + e);
//            throw new DaoException(e);
//        }
//    }
}
