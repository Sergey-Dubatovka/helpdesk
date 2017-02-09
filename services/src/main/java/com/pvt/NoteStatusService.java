package com.pvt;

import com.pvt.beans.Note;
import com.pvt.beans.NoteStatus;
import com.pvt.dao.NoteStatusDAO;
import com.pvt.dao.exceptions.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by sssergey83 on 16.01.2017.
 */

public class NoteStatusService implements IService<NoteStatus> {
    private static final Logger LOG = LoggerFactory.getLogger(NoteStatusService.class);
    private static NoteStatusDAO dao = NoteStatusDAO.getDao();

    private static NoteStatusService service = null;

    public static synchronized NoteStatusService getService() {
        if (service == null) {
            service = new NoteStatusService();
        }
        return service;
    }

    @Override
    public NoteStatus saveOrUpdate(NoteStatus noteStatus) {
        try {
            util.beginTransaction();
            dao.saveOrUpdate(noteStatus);
            LOG.info("SaveOrUpdate noteStatusService commit:" + noteStatus);
            util.commit();
        } catch (DaoException e) {
            LOG.error("Error NoteStatusService SaveOrUpdate:" + noteStatus, e);
            util.rollback();
            return null;
        }
        return noteStatus;
    }

    @Override
    public NoteStatus get(Serializable id) {
        NoteStatus noteStatus;
        try {
            util.beginTransaction();
            noteStatus = dao.get(id);
            LOG.info("Get noteStatus commit:" + noteStatus);
            util.commit();
        } catch (DaoException e) {
            LOG.error("Error get noteStatus commit" + e.getMessage(), e);
            util.rollback();
            return null;
        }
        return noteStatus;
    }

    @Override
    public NoteStatus load(Serializable id) {
        NoteStatus noteStatus = null;
        try {
            util.beginTransaction();
            noteStatus = dao.load(id);
        } catch (DaoException e) {
            LOG.error("Error load noteStatus commit" + e.getMessage(), e);
            util.rollback();
            return null;
        }
        return noteStatus;
    }

    @Override
    public boolean delete(NoteStatus noteStatus) {
        try {
            util.beginTransaction();
            dao.delete(noteStatus);
            LOG.info("Delete noteStatus commit:" + noteStatus);
           util.commit();
        } catch (DaoException e) {
            util.rollback();
            LOG.error("Error delete noteStatus commit:" + e.getMessage(), e);
            return false;
        }
        return true;
    }

    @Override
    public List<NoteStatus> find(String hql) {
        try {
            util.beginTransaction();
            List<NoteStatus> statuses = dao.find(hql);
            LOG.info("NoteStatus find() commit.");
            util.commit();
            return statuses;
        } catch (DaoException e) {
            LOG.error("Error NoteStatus find() commit." + e.getMessage(), e);
            util.rollback();
            return null;
        }
    }
    @Override
    public Set<NoteStatus> getAll()  {
        try {
            util.beginTransaction();
            Set<NoteStatus> statuses = dao.getAll();
            LOG.info("NoteStatus find() commit.");
            util.commit();
            return statuses;
        } catch (DaoException e) {
            LOG.error("Error NotePriority find() commit." + e.getMessage(), e);
            util.rollback();
            return null;
        }
    }
}
