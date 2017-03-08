package com.pvt.services;

import com.pvt.beans.UserRole;
import com.pvt.dao.interfaces.IUserDAO;
import com.pvt.dao.interfaces.IUserRoleDAO;
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
@Transactional(propagation = Propagation.REQUIRED)
public class UserRoleService extends BaseService<UserRole> {

    private static final Logger LOG = LoggerFactory.getLogger(UserRoleService.class);

    @Autowired
    IUserRoleDAO userRoleDAO;

}
