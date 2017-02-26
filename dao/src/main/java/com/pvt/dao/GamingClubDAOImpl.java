package com.pvt.dao;

import com.pvt.beans.GamingClub;
import com.pvt.dao.exceptions.DaoException;
import com.pvt.dao.interfaces.IGamingClubDAO;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created on 05.02.2017.
 */
@Repository
public class GamingClubDAOImpl extends BaseDaoImpl<GamingClub> implements IGamingClubDAO {
    private static final Logger LOG = LoggerFactory.getLogger(GamingClubDAOImpl.class);

    @Autowired
    public GamingClubDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public GamingClub find(String where) throws DaoException {
        try {
            String hql = "FROM GamingClub GC WHERE GC.gamingClubName=:gamingClubName";
            Query query = getSession().createQuery(hql);
            query.setParameter("gamingClubName", where);
            query.setCacheable(true);

            return (GamingClub) query.uniqueResult();
        } catch (HibernateException e) {
            LOG.error("Error find(): " + e);
            throw new DaoException(e);
        }
    }
}
