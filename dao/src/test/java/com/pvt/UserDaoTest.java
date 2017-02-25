package com.pvt;

import com.pvt.beans.User;
import com.pvt.beans.UserRole;
import com.pvt.dao.exceptions.DaoException;
import com.pvt.dao.interfaces.IUserDAO;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.transaction.Transactional;

import static com.pvt.BaseDaoTest.createUser;

/**
 * Unit test for Dao.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(locations = "classpath:daoTestSpring.xml")
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)

public class UserDaoTest extends TestCase {
    private User user = new User();
    String login = "userForTest";

    @Autowired
    public IUserDAO userDAO;

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

    public void testB_FindUser() throws DaoException {
        user = new User("test", "test", "test@mail.ru", null);
        userDAO.saveOrUpdate(user);
        userDAO.find(login + "Find");
        Assert.assertNotNull(user.getUserId());
    }

    public void testF_CountAllUsers() throws DaoException {
        Long result;
        result = userDAO.countAllUsers();
        Assert.assertNotNull(result);
    }

    public void testG_CountUserByRole() throws DaoException {
        String[] roles = new String[]{"Manager", "Director", "Worker"};
        Long result;

        for (String role : roles) {
            user = createUser(login, role);
            user.setUserRole(new UserRole(role));
            userDAO.saveOrUpdate(user);
            result = userDAO.countUserByRole(role);
            Assert.assertNotNull(result);
        }
    }
}