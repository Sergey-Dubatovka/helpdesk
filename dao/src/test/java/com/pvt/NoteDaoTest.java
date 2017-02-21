package com.pvt;

import com.pvt.beans.*;
import com.pvt.dao.DAO;
import com.pvt.dao.exceptions.DaoException;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Unit test for Dao.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NoteDaoTest extends TestCase {
    private static DAO dao = DAO.getDAO();
    private static Logger log = LoggerFactory.getLogger(NoteDaoTest.class);
    private static Note note;
    private static GamingClub gamingClub;
    private static NotePriority notePriority;
    private static NoteStatus noteStatus;
    private static User user;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public NoteDaoTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(NoteDaoTest.class);
    }


}
