package com.pvt;

import com.pvt.beans.Note;
import com.pvt.beans.User;
import com.pvt.dao.NoteDAO;
import com.pvt.dao.exceptions.DaoException;
import com.pvt.dao.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by sssergey83 on 16.01.2017.
 */

public class NoteService extends Service<Note> {
    private static final Logger LOG = LoggerFactory.getLogger(NoteService.class);
    private static NoteDAO dao = NoteDAO.getDao();

    private static NoteService service = null;
    Transaction t = null;

    public static synchronized NoteService getService() {
        if (service == null) {
            service = new NoteService();
        }
        return service;
    }

    @Override
    public Note saveOrUpdate(Note note) {
        Date date = new Date();
        Session session = util.getSession();
        t = session.beginTransaction();
        try {
            note.setDate(date);
            dao.saveOrUpdate(note);
            LOG.info("SaveOrUpdate note commit:" + note);
            t.commit();
            session.flush();
        } catch (DaoException e) {
            LOG.error("Error NoteService SaveOrUpdate:" + note, e);
            t.rollback();
            return null;
        }
        return note;
    }

    @Override
    public Note get(Serializable id) {
        Note note;
        Session session = util.getSession();
        t = session.beginTransaction();
        try {
            note = dao.get(id);
            LOG.info("Get note commit:" + note);
            t.commit();
            session.flush();
        } catch (DaoException e) {
            LOG.error("Error get commit" + e.getMessage(), e);
            t.rollback();
            return null;
        }
        return note;
    }

    @Override
    public Note find(String hql) {
        Session session = util.getSession();
        t = session.beginTransaction();
        try {
            Note note = dao.find(hql);
            LOG.info("Note find() commit.");
            t.commit();
            session.flush();
            return note;
        } catch (DaoException e) {
            LOG.error("Error Note find() commit." + e.getMessage(), e);
            t.rollback();
            return null;
        }
    }

    @Override
    public Set<Note> getAll() {
        Session session = util.getSession();
        t = session.beginTransaction();
        try {
            Set<Note> statuses = dao.getAll("Note");
            LOG.info("NotePriority find() commit.");
            t.commit();
            session.flush();
            return statuses;
        } catch (DaoException e) {
            LOG.error("Error NotePriority find() commit." + e.getMessage(), e);
            t.rollback();
            return null;
        }
    }

    public List<Note> findUserNotes(Long id) {
        Session session = util.getSession();
        t = session.beginTransaction();
        List<Note> notes = null;
        try {
            notes = dao.findUserNotes(id);
            t.commit();
            session.flush();
        } catch (DaoException e) {
            LOG.error("Error findUserNotes in NoteDao" + e.getMessage());
            t.rollback();
        }
        return notes;
    }
    public List<Note> getAllOpen() {
        Session session = util.getSession();
        t = session.beginTransaction();
        List<Note> notes = null;
        try {
            notes = dao.getAllOpen();
            t.commit();
            session.flush();
        } catch (DaoException e) {
            LOG.error("Error findUserNotes in NoteDao" + e.getMessage());
            t.rollback();
        }
        return notes;
    }

}
