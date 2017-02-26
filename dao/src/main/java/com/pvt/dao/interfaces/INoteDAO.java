package com.pvt.dao.interfaces;

import com.pvt.beans.Note;
import com.pvt.dao.exceptions.DaoException;

import java.util.List;

/**
 * Created by sssergey83 on 23.02.2017.
 */
public interface INoteDAO extends IDao<Note> {
    List<Note> findUserNotes(Long id) throws DaoException;

    List<Note> getAllOpen() throws DaoException;

    Long countAll() throws DaoException;

    Long countAllOpen() throws DaoException;

    Long countInProgress() throws DaoException;

    Long countResolved() throws DaoException;

    List<Note> getPage(int pageIndex, int numberOfRecordsPerPage) throws DaoException;

}
