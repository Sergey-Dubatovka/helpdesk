package com.pvt;

import com.pvt.beans.User;
import com.pvt.beans.UserRole;
import com.pvt.dao.exceptions.DaoException;
import com.pvt.dao.interfaces.IDao;
import com.pvt.dao.interfaces.IUserDAO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.transaction.Transactional;

/**
 * Unit test for Dao.
 */

@ContextConfiguration(locations = "classpath:daoTestSpring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)

public class BaseDaoTest {
    private static User user = new User();
    String login = "userForTest";

    @Autowired
    private IDao<User> baseDao;

    @Autowired
    private IUserDAO userDAO;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
//    @Test
//    public BaseDaoTest(String testName) {
//        super(testName);
//    }

    /**
     * @return the suite of tests being tested
     */
//    public static Test suite() {
//        return new TestSuite(BaseDaoTest.class);
//    }
@Test
    public void testA_SaveUser() throws DaoException {

        user = createUser(login, "");
        baseDao.saveOrUpdate(user);
        Assert.assertNotNull(user.getUserId());
    }

    public void testC_UpdateUser() throws DaoException {
        UserRole userRole = new UserRole("UpdatedRole");
        Long userId = 1L;
        String updLogin = "userForTestNew";
        user = (User) baseDao.get(userId);
        user.setEmail(updLogin + "@mail.ru");
        user.setLogin(updLogin);
        user.setPassword(updLogin + "pass");
        user.setUserRole(userRole);

        baseDao.saveOrUpdate(user);
        User updatedUser = (User) userDAO.find(updLogin);
        Assert.assertEquals(updatedUser.getUserId(), user.getUserId());
    }

    public void testD_GetUser() throws DaoException {
        user = createUser(login, "Get");
        user = (User) baseDao.get(1L);
        Assert.assertNotNull(user.getUserId());
    }

    public void testE_LoadUser() throws DaoException {
        user = createUser(login, "Load");
        baseDao.load(user.getUserId());
        Assert.assertNotNull(user.getUserId());
    }

    public void testH_deleteUser() throws DaoException {
        user = createUser(login, "delete");
        baseDao.saveOrUpdate(user);
        Assert.assertNotNull(user);
        Assert.assertTrue(baseDao.delete(user));
    }

    static User createUser(String login, String suffix) {
        UserRole testRole = new UserRole("RoleFor" + login + suffix);

        user.setLogin(login + suffix);
        user.setPassword(login + suffix);
        user.setEmail(login + suffix + "@mailForTest.by");
        user.setUserRole(testRole);
        return user;
    }
}