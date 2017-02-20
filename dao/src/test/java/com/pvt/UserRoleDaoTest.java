package com.pvt;

import com.pvt.beans.UserRole;
import com.pvt.dao.UserRoleDAO;
import com.pvt.dao.exceptions.DaoException;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit test for Dao.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserRoleDaoTest extends TestCase {
    private static UserRoleDAO dao = UserRoleDAO.getDao();
    private static Logger log = LoggerFactory.getLogger(UserRoleDaoTest.class);
    private static UserRole role;


    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public UserRoleDaoTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(UserRoleDaoTest.class);
    }

    public void testA_SaveUserRole() {

        role = new UserRole("Admin");
        try {
            dao.saveOrUpdate(role);
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new Error(e.getMessage());
        }
        Assert.assertNotNull(role.getRoleId());
    }

    public void testB_FindUserRole() {
        try {
            role = dao.find("Admin");
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new Error(e.getMessage());
        }
        Assert.assertNotNull(role.getRoleId());
    }

    public void testC_UpdateUser() {
        role.setRoleName("Administrator");
        UserRole updatedRole = role;
        try {
            role = dao.saveOrUpdate(updatedRole);
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new Error(e.getMessage());
        }
        Assert.assertEquals(updatedRole, role);
    }

    public void testD_GetUserRole() {
        UserRole currentRole = role;
        try {
            role = dao.get(role.getRoleId());
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new Error(e.getMessage());
        }
        Assert.assertEquals(currentRole, role);
    }

    public void testE_LoadUserRole() {
        UserRole currentUserRole = role;
        try {
            role = dao.load(role.getRoleId());
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new Error(e.getMessage());
        }
        Assert.assertEquals(currentUserRole, role);
    }

    public void testH_deleteUserRole() {
        Assert.assertNotNull(role);
        try {
            Assert.assertTrue(dao.delete(role));
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new Error(e.getMessage());
        }
    }

}
