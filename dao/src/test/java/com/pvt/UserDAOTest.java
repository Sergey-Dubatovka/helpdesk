package com.pvt;

import com.pvt.beans.IRole;
import com.pvt.beans.User;
import com.pvt.dao.DAO;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import java.util.Random;

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class UserDAOTest extends TestCase {
    static DAO dao = DAO.getDAO();
    static User user = new User();


    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public UserDAOTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(UserDAOTest.class);
    }

    /**
     * Rigourous Test :-)
     */

    public void testA_CreateUser() {
        // create
        Random random = new Random();
        int random500 = random.nextInt(500);
        user.setLogin("testLogin" + random500);
        user.setPassword("testPassword"+random500);
        user.setEmail("testEmail@gmail.com");
        user.setFk_role(IRole.roleManager);

        boolean id = dao.user.create(user);
        assertTrue(id);
    }

    public void testB_UpdateUser() {
        // update
        user.setPassword("testPasswordNew");
        user.setEmail("testEmailNew@gmail.com");
        user.setFk_role(IRole.roleWorker);

        boolean updated = dao.user.update(user);
        assertTrue(updated);
    }

    public void testC_ReadUser() {
        // read
        User updatedUser = dao.user.read(user.getID());

        assertEquals("testPasswordNew", updatedUser.getPassword());
        assertEquals("testEmailNew@gmail.com", updatedUser.getEmail());
        assertEquals(IRole.roleWorker, updatedUser.getFk_role());
    }

    public void testD_DeleteUser() {
        // delete
        boolean deleted = dao.user.delete(user);
        assertTrue(deleted);
    }
}