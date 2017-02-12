package com.pvt;

import com.pvt.beans.GamingClub;
import com.pvt.beans.User;
import com.pvt.dao.GamingClubDAO;
import com.pvt.dao.exceptions.DaoException;
import com.pvt.dao.util.HibernateUtil;
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
public class GamingClubService extends Service<GamingClub> {

    private static final Logger LOG = LoggerFactory.getLogger(GamingClubService.class);
    private static GamingClubDAO dao = GamingClubDAO.getDao();

    private static GamingClubService service = null;
    Transaction t = null;

    public static synchronized GamingClubService getService() {
        if (service == null) {
            service = new GamingClubService();
        }
        return service;
    }

    @Override
    public GamingClub saveOrUpdate(GamingClub gamingClub) {
        try {
            Session session = util.getSession();
            t = session.beginTransaction();
            dao.saveOrUpdate(gamingClub);
            LOG.info("SaveOrUpdate commit:" + gamingClub);
            t.commit();
            session.flush();
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            t.rollback();
            return null;
        }
        return gamingClub;
    }

    @Override
    public GamingClub get(Serializable id) {
        GamingClub gamingClub;
        try {
            Session session = util.getSession();
            t = session.beginTransaction();
            gamingClub = dao.get(id);
            LOG.info("Get commit:" + gamingClub);
            t.commit();
            session.flush();
        } catch (DaoException e) {
            LOG.error("Error get commit:" + e.getMessage(), e);
            t.rollback();
            return null;
        }
        return gamingClub;
    }

    @Override
    public GamingClub find(String str) {
        GamingClub club = null;
        try {
            Session session = util.getSession();
            t = session.beginTransaction();
            club = dao.find(str);
            t.commit();
            session.flush();
        } catch (DaoException e) {
            t.rollback();
            LOG.error("" + e);
        }
        return club;
    }

    /**
     * @return list of All Gaming clubs
     */
    @Override
    public Set<GamingClub> getAll() {

        Set<GamingClub> gamingClubs = null;
        Session session = HibernateUtil.getHibernateUtil().getSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            gamingClubs = dao.getAll();
            t.commit();
            session.flush();

        } catch (DaoException e) {
            t.rollback();
            LOG.error("" + e);
        }
        return gamingClubs;
    }
}
