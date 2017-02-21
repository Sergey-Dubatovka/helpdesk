package com.pvt;

import com.pvt.beans.User;
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

/**
 * Unit test for Dao.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoTest extends TestCase {
    private static DAO dao = DAO.getDAO();
    private static Logger log = LoggerFactory.getLogger(UserDaoTest.class);
    static User user;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public UserDaoTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(UserDaoTest.class);
    }

    public void testA_SaveUser() {

        user = createUserForTests();
        try {
            dao.user.saveOrUpdate(user);
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new Error(e.getMessage());
        }
        Assert.assertNotNull(user.getUserId());
    }

    public void testB_FindUser() {
        User userFindTest;
        try {
             userFindTest = dao.user.find("userForTest");
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new Error(e.getMessage());
        }
        Assert.assertEquals(user, userFindTest);
    }

    public void testC_UpdateUser() {
        User updatedUser= null;
        try {
            updatedUser = dao.user.find(user.getLogin());
        } catch (DaoException e) {
            e.printStackTrace();
        }
        updatedUser.setEmail("userForTestNew");
        updatedUser.setLogin("userForTestNew");
        updatedUser.setPassword("userForTestNew");

        try {
            updatedUser.setUserRole(dao.role.get(2L));
         user = dao.user.saveOrUpdate(updatedUser);
                    } catch (DaoException e) {
            log.error(e.getMessage());
            throw new Error(e.getMessage());
        }
        Assert.assertEquals(updatedUser, user);

    }

    public void testD_GetUser() {
        User currentUser = user;
        try {
            dao.user.get(user.getUserId());
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new Error(e.getMessage());
        }
        Assert.assertEquals(currentUser, user);
    }

    public void testE_LoadUser() {
        User currentUser = user;
        try {
            dao.user.load(user.getUserId());
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new Error(e.getMessage());
        }
        Assert.assertEquals(currentUser, user);
    }


    public void testF_CountAllUsers() {
        Long result;
        try {
            result = dao.user.countAllUsers();
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new Error(e.getMessage());
        }
        Assert.assertNotNull(result);
    }

    public void testG_CountUserByRole() {
        String[] role = new String[]{"Manager", "Director", "Worker"};
        Long result;
        try {
            for (String elem : role) {
                result = dao.user.countUserByRole(elem);
                Assert.assertNotNull(result);
            }
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new Error(e.getMessage());
        }
    }

    public void testH_deleteUser() {
        Assert.assertNotNull(user);
        try {
            Assert.assertTrue(dao.user.delete(user));
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new Error(e.getMessage());
        }
    }

    User createUserForTests() {
        if (user == null) {
            user = new User();
            user.setLogin("userForTest");
            user.setPassword("userForTest");
            user.setEmail("userForTest@mailForTest.by");
            try {
                user.setUserRole(dao.role.get(1L));
            } catch (DaoException e) {
                log.error(e.getMessage());
                throw new Error(e.getMessage());
            }
        }
        return user;
    }
}
