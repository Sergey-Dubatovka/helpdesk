package com.pvt.services;

import com.pvt.beans.GamingClub;
import com.pvt.dao.interfaces.IGamingClubDAO;
import com.pvt.dao.interfaces.IUserDAO;
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
public class GamingClubService extends BaseService<GamingClub> {

    @Autowired
    IGamingClubDAO gamingClubDAO;

    private static final Logger LOG = LoggerFactory.getLogger(GamingClubService.class);

}
