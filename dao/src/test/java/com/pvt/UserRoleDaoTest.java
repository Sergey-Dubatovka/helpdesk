package com.pvt;

import com.pvt.beans.UserRole;
import com.pvt.dao.interfaces.IUserRoleDAO;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.transaction.Transactional;

/**
 * Unit test for Dao.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(locations = "classpath:daoTestSpring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class UserRoleDaoTest {

    private static String testRoleAdmin = "Admin";
    private static UserRole role = new UserRole(testRoleAdmin);

    @Autowired
    public IUserRoleDAO roleDAO;

    @Test
    public void testA_saveOrUpdate() throws Exception {
        role = new UserRole(testRoleAdmin);
        roleDAO.saveOrUpdate(role);

        Assert.assertNotNull(role.getRoleId());
    }

    @Test
    public void testC_find() throws Exception {
        role = roleDAO.find(testRoleAdmin);
        System.out.println(role);
        Assert.assertNotNull(role.getRoleId());
    }

    @Test
    public void testB_Get() throws Exception {
        role = roleDAO.get(UserRole.class, 1L);
        Assert.assertNotNull(role);
    }

    @Test
    public void testH_getAllTest() throws Exception {
        Assert.assertNotNull(roleDAO.getAll(UserRole.class));
    }

//    @Test
//    public void testH_delete() throws Exception {
//                Assert.assertTrue(roleDAO.delete(role));
//    }
}
