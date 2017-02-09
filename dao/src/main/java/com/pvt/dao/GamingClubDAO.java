package com.pvt.dao;

import com.pvt.beans.GamingClub;
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
public class GamingClubDAO extends BaseDao<GamingClub> {
    private static final Logger LOG = LoggerFactory.getLogger(GamingClubDAO.class);

    private static GamingClubDAO dao = null;

    public static synchronized GamingClubDAO getDao() {
        if (dao == null) {
            dao = new GamingClubDAO();
        }
        return dao;
    }

    @Override
    public List<GamingClub> find(String where) throws DaoException {
        try {
            String hql = "FROM GamingClub GS WHERE GS.gamingClubName=:gamingClubName";
            Query query = util.getSession().createQuery(hql);
            query.setParameter("gamingClubName", where);
            List<GamingClub> clubs = query.list();
            return clubs;
        } catch (HibernateException e) {
            LOG.error("Error find(): " + e);
            throw new DaoException(e);
        }
    }

    public Set<GamingClub> getAll() throws DaoException {
        try {
            String hql = "FROM GamingClub";
            Query query = util.getSession().createQuery(hql);
            List<GamingClub> clubs = query.list();
            Set<GamingClub> gamingClubs = new HashSet<>();
            gamingClubs.addAll(clubs);
            return gamingClubs;
        } catch (HibernateException e) {
            LOG.error("Error find(): " + e);
            throw new DaoException(e);
        }
    }

}
