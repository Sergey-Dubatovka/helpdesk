package com.pvt;

import com.pvt.beans.Priority;
import com.pvt.dao.DAO;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class PriorityDAOTest extends TestCase {
    static DAO dao = DAO.getDAO();
    static Priority priority = new Priority();

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public PriorityDAOTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(PriorityDAOTest.class);
    }

    /**
     * Rigourous Test :-)
     */

    public void testA_CreatePriority() {
        // create
        priority.setPriorityName("middle");
        boolean id = dao.priority.create(priority);
        assertTrue(id);
    }

    public void testB_UpdatePriority() {
        // update
        priority.setPriorityName("middle2");

        boolean updated = dao.priority.update(priority);
        assertTrue(updated);
    }

    public void testC_ReadPriority() {
        // read
        Priority updatedPriority = dao.priority.read(priority.getID());
        assertEquals("middle2", updatedPriority.getPriorityName());
    }

    public void testD_DeletePriority() {
        // delete
        boolean deleted = dao.priority.delete(priority);
        assertTrue(deleted);
    }
}