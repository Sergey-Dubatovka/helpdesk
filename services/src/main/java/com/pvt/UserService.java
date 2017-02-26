package com.pvt;

import com.pvt.beans.User;
import com.pvt.dao.exceptions.DaoException;
import com.pvt.dao.interfaces.IUserDAO;
import com.pvt.exceptions.ServiceException;
import com.pvt.interfaces.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by sssergey83 on 16.01.2017.
 */
public class UserService extends BaseService<User> implements IUserService {

    private static Logger log = LoggerFactory.getLogger(UserService.class);
    private static final String salt = "salt";

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
