package com.pvt;

import com.pvt.beans.GamingClub;
import com.pvt.dao.GamingClubDAO;
import com.pvt.dao.exceptions.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sssergey83 on 16.01.2017.
 */
public class GamingClubService implements IService<GamingClub> {

    private static final Logger LOG = LoggerFactory.getLogger(GamingClubService.class);
    private static GamingClubDAO dao = GamingClubDAO.getDao();

    @Override
    public GamingClub saveOrUpdate(GamingClub gamingClub) {
        try {
            util.beginTransaction();
            dao.saveOrUpdate(gamingClub);
            LOG.info("SaveOrUpdate commit:" + gamingClub);
            util.commit();
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            util.rollback();
            return null;
        }
        return gamingClub;
    }

    @Override
    public GamingClub get(Serializable id) {
        GamingClub gamingClub;
        try {
            util.beginTransaction();
            gamingClub = dao.get(id);
            LOG.info("Get commit:" + gamingClub);
            util.commit();
        } catch (DaoException e) {
            LOG.error("Error get commit:" + e.getMessage(), e);
            util.rollback();
            return null;
        }
        return gamingClub;
    }

    @Override
    public GamingClub load(Serializable id) {
        GamingClub gamingClub = null;
        try {
            util.beginTransaction();
            gamingClub = dao.load(id);
            LOG.info("Load commit:" + gamingClub);
            util.commit();
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            util.rollback();
            return null;
        }
        return gamingClub;
    }

    @Override
    public boolean delete(GamingClub gamingClub) {
        try {
            util.beginTransaction();
            dao.delete(gamingClub);
            LOG.info("Delete commit:" + gamingClub);
            util.commit();
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            util.rollback();
            return false;
        }
        return true;
    }

    @Override
    public List<GamingClub> find(String where) {
        try {
            util.beginTransaction();
            List<GamingClub> gamingClubs = dao.find(where);
            LOG.info("gamingClubs find() commit.");
            util.commit();
            return gamingClubs;
        } catch (DaoException e) {
            LOG.error("Error gamingClubs find() commit." + e.getMessage(), e);
            util.rollback();
            return null;
        }
    }


    /**
     *
     * @return list of All Gaming clubs
     */
        public List<GamingClub> getAll() {
        try {
            util.beginTransaction();
            List<GamingClub> gamingClubs = dao.getAll();
            LOG.info("gamingClubs find() commit.");
            util.commit();
            return gamingClubs;
        } catch (DaoException e) {
            LOG.error("Error gamingClubs find() commit." + e.getMessage(), e);
            util.rollback();
            return null;
        }
    }
}
