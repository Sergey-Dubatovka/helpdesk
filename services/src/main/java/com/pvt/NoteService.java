package com.pvt;

import com.pvt.beans.Note;
import com.pvt.dao.interfaces.INoteDAO;
import com.pvt.exceptions.ServiceException;
import com.pvt.interfaces.INoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by sssergey83 on 16.01.2017.
 */

public class NoteService extends BaseService<Note> implements INoteService {
    private static Logger log = LoggerFactory.getLogger(NoteService.class);

    @Autowired
    INoteDAO noteDAO;

    @Override
    public List<Note> findUserNotes(Long id) throws ServiceException {
        try {
            return noteDAO.findUserNotes(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Note> getAllOpen() throws ServiceException {
            try {
                return noteDAO.getAllOpen();
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new ServiceException(e);
            }
        }

    @Override
    public Long countAll() throws ServiceException{
        try {
            return noteDAO.countAll();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public Long countAllOpen()throws ServiceException {
        try {
            return noteDAO.countAllOpen();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public Long countInProgress() throws ServiceException{
        try {
            return noteDAO.countInProgress();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public Long countResolved() throws ServiceException {
        try {
            return noteDAO.countResolved();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Note> getPage(int pageIndex, int numberOfRecordsPerPage) throws ServiceException{
        try {
            return noteDAO.getPage(pageIndex, numberOfRecordsPerPage);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
    }
}