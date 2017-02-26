package com.pvt;

import com.pvt.beans.User;
import com.pvt.dao.UserRoleDAOImpl;
import com.pvt.dao.interfaces.IDao;
import com.pvt.dao.interfaces.IUserRoleDAO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static Logger log = LoggerFactory.getLogger(UserRoleDAOImpl.class);

    private static User user;
    String login = "testUser";

    @Autowired
    private IDao<User> baseDao;

    @Autowired
    private IUserRoleDAO roleDao;

    @Test
    public void testA_saveOrUpdate() throws Exception {
        user = createUser(login, "");
        baseDao.saveOrUpdate(user);
        Assert.assertNotNull(user.getUserId());
    }

    @Test
    public void testD_GetUser() throws Exception {
        user = createUser(login, "Get");
        user = baseDao.get(User.class, 1L);
        Assert.assertNotNull(user.getUserId());
    }

    User createUser(String login, String suffix) {
        if (user != null) {
            return user;
        }
        user = new User();
        user.setLogin(login + suffix);
        user.setPassword(login + suffix);
        user.setEmail(login + suffix + "@testMail.by");
        user.setUserRole(null);
        return user;
    }
}