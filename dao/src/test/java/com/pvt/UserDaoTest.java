package com.pvt;

import com.pvt.beans.User;
import com.pvt.dao.exceptions.DaoException;
import com.pvt.dao.interfaces.IUserDAO;
import com.pvt.dao.interfaces.IUserRoleDAO;
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
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(locations = "classpath:daoTestSpring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)

public class UserDaoTest {
    private User user;
    String login;

    @Autowired
    public IUserDAO userDAO;

    @Autowired
    public IUserRoleDAO roleDAO;

    @Test
    public void testB_FindUser() throws Exception {
        login = "test";
        user = userDAO.find(login);
        Assert.assertNotNull(user.getUserId());
    }

    @Test
    public void testF_CountAllUsers() throws Exception {
        Long result;
        result = userDAO.countAllUsers();
        Assert.assertNotNull(result);
    }

    @Test
    public void testG_CountUserByRole() throws DaoException {
        String[] roles = new String[]{"Manager", "Director", "Worker"};
        Long result;

        for (String role : roles) {
            result = userDAO.countUserByRole(role);
            Assert.assertNotNull(result);
        }
    }
    @Test
    public void testH_getAllTest() throws DaoException {
        Assert.assertNotNull(userDAO.getAll(User.class));
    }
}