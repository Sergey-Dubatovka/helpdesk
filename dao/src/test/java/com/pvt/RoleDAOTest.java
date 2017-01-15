package com.pvt;

import com.pvt.beans.Role;
import com.pvt.dao.DAO;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class RoleDAOTest extends TestCase {
    static DAO dao = DAO.getDAO();
    static Role role = new Role();

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public RoleDAOTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(RoleDAOTest.class);
    }

    /**
     * Rigourous Test :-)
     */

    public void testA_CreateRole() {
        // create
        role.setRoleName("administrator");
        boolean id = dao.role.create(role);
        assertTrue(id);
    }

    public void testB_UpdateRole() {
        // update
        role.setRoleName("admin");

        boolean updated = dao.role.update(role);
        assertTrue(updated);
    }

    public void testC_ReadRole() {
        // read
        Role updatedRole = dao.role.read(role.getID());
        assertEquals("admin", updatedRole.getRoleName());
    }

    public void testD_DeleteRole() {
        // delete
        boolean deleted = dao.role.delete(role);
        assertTrue(deleted);
    }
}