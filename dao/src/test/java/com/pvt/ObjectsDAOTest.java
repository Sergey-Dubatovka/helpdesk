package com.pvt;

import com.pvt.beans.Objects;
import com.pvt.dao.DAO;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class ObjectsDAOTest extends TestCase {
    static DAO dao = DAO.getDAO();
    static Objects objects = new Objects();

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ObjectsDAOTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(ObjectsDAOTest.class);
    }

    /**
     * Rigourous Test :-)
     */

    public void testA_CreateObjects() {
        // create
        objects.setZia("Mнс:Пассаж");
        boolean id = dao.objects.create(objects);
        assertTrue(id);
    }

    public void testB_UpdateObjects() {
        // update
        objects.setZia("Mнс:Пассаж2");

        boolean updated = dao.objects.update(objects);
        assertTrue(updated);
    }

    public void testC_ReadObjects() {
        // read
        Objects updatedObjects = dao.objects.read(objects.getID());
        assertEquals("Mнс:Пассаж2", updatedObjects.getZia());
    }

    public void testD_DeleteObjects() {
        // delete
        boolean deleted = dao.objects.delete(objects);
        assertTrue(deleted);
    }
}