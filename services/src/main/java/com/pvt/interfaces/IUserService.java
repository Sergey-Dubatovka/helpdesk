package com.pvt.interfaces;

import com.pvt.beans.User;
import com.pvt.exceptions.ServiceException;

/**
 * Created on 26.02.2017.
 */
public interface IUserService extends IService<User> {

    Long countAllUsers() throws ServiceException;

    Long countUserByRole(String role) throws ServiceException;
}
