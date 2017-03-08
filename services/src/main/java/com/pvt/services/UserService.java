package com.pvt.services;

import com.pvt.beans.User;
import com.pvt.dao.exceptions.DaoException;
import com.pvt.dao.interfaces.IUserDAO;

import com.pvt.services.exceptions.ServiceException;
import com.pvt.services.interfaces.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created on 16.01.2017.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class UserService extends BaseService<User> implements IUserService {

    private static Logger log = LoggerFactory.getLogger(UserService.class);
 //   private static final String salt = "salt";

    @Autowired
    IUserDAO userDAO;

    @Override
    public Long countAllUsers() throws ServiceException {
        try {
            return userDAO.countAllUsers();
        } catch (DaoException e) {
            log.error("Error in countAllUsers UserService" + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public Long countUserByRole(String role) throws ServiceException {
        try {
            return userDAO.countUserByRole(role);
        } catch (DaoException e) {
            log.error("error in countUserByRole in UserService()");
            throw new ServiceException(e);
        }
    }
}