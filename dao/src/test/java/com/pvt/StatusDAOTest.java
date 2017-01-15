package com.pvt;

import com.pvt.beans.Status;
import com.pvt.dao.DAO;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class StatusDAOTest extends TestCase {
    static DAO dao = DAO.getDAO();
    static Status status = new Status();

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public StatusDAOTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(StatusDAOTest.class);
    }

    /**
     * Rigourous Test :-)
     */

    public void testA_CreateStatus() {
        // create
        status.setStatusName("newStatus");
        boolean id = dao.status.create(status);
        assertTrue(id);
    }

    public void testB_UpdateStatus() {
        // update
        status.setStatusName("newStatus2");

        boolean updated = dao.status.update(status);
        assertTrue(updated);
    }

    public void testC_ReadStatus() {
        // read
        Status updatedStatus = dao.status.read(status.getID());
        assertEquals("newStatus2", updatedStatus.getStatusName());
    }

    public void testD_DeleteStatus() {
        // delete
        boolean deleted = dao.status.delete(status);
        assertTrue(deleted);
    }
}