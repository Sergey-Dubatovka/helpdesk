package com.pvt;

import com.pvt.beans.NoteStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sssergey83 on 16.01.2017.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class NoteStatusService extends BaseService<NoteStatus> {
    private static final Logger LOG = LoggerFactory.getLogger(NoteStatusService.class);

}
