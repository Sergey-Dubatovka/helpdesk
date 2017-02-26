package com.pvt.interfaces;

import com.pvt.beans.Note;
import com.pvt.exceptions.ServiceException;

import java.util.List;

/**
 * Created on 26.02.2017.
 */
public interface INoteService extends IService<Note> {
    List<Note> findUserNotes(Long id) throws ServiceException;

    List<Note> getAllOpen() throws ServiceException;

    Long countAll() throws ServiceException;

    Long countAllOpen() throws ServiceException;

    Long countInProgress() throws ServiceException;

    Long countResolved() throws ServiceException;

    List<Note> getPage(int pageIndex, int numberOfRecordsPerPage) throws ServiceException;
}
