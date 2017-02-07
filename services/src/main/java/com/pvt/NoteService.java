package com.pvt;

import com.pvt.beans.Note;

import com.pvt.dao.NoteDAO;
import com.pvt.dao.exceptions.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.Serializable;
import java.util.List;

/**
 * Created by sssergey83 on 16.01.2017.
 */

public class NoteService implements IService<Note> {
    private static final Logger LOG = LoggerFactory.getLogger(NoteService.class);
    private static NoteDAO dao = NoteDAO.getDao();

    @Override
    public Note saveOrUpdate(Note note) {
        try {
          util.beginTransaction();
            dao.saveOrUpdate(note);
            LOG.info("SaveOrUpdate note commit:" + note);
            util.commit();
        } catch (DaoException e) {
            LOG.error("Error NoteService SaveOrUpdate:" + note, e);
            util.rollback();
            return null;
        }
        return note;
    }

    @Override
    public Note get(Serializable id) {
        Note note;
        try {
           util.beginTransaction();
            note = dao.get(id);
            LOG.info("Get note commit:" + note);
           util.commit();
        } catch (DaoException e) {
            LOG.error("Error get commit" + e.getMessage(), e);
          util.rollback();
            return null;
        }
        return note;
    }

    @Override
    public Note load(Serializable id) {
        Note note = null;
        try {
           util.beginTransaction();
            note = dao.load(id);
        } catch (DaoException e) {
            LOG.error("Error load commit" + e.getMessage(), e);
           util.rollback();
            return null;
        }
        return note;
    }

    @Override
    public boolean delete(Note note) {
        try {
          util.beginTransaction();
            dao.delete(note);
            LOG.info("Delete note commit:" + note);
          util.commit();
        } catch (DaoException e) {
            util.rollback();
            LOG.error("Error delete note commit:" + e.getMessage(), e);
            return false;
        }
        return true;
    }

    @Override
    public List<Note> find(String hql) {
        try {
            util.beginTransaction();
            List<Note> notes = dao.find(hql);
            LOG.info("Note find() commit.");
            util.commit();
            return notes;
        } catch (DaoException e) {
            LOG.error("Error Note find() commit." + e.getMessage(), e);
            util.rollback();
            return null;
        }
    }
}
