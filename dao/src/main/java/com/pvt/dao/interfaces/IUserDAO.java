package com.pvt.dao.interfaces;

import com.pvt.beans.User;
import com.pvt.dao.exceptions.DaoException;

/**
 * Created  on 23.02.2017.
 */
public interface IUserDAO extends IDao<User> {
    Long countAllUsers() throws DaoException;

    Long countUserByRole(String role) throws DaoException;

}
