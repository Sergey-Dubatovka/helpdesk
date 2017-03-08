package com.pvt.services;

import com.pvt.beans.NotePriority;
import com.pvt.dao.interfaces.INotePriorityDAO;
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
public class NotePriorityService extends BaseService<NotePriority> {
    private static final Logger LOG = LoggerFactory.getLogger(NotePriorityService.class);

    @Autowired
    INotePriorityDAO notePriorityDAO;

}
