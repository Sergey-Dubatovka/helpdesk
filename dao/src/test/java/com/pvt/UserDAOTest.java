package com.pvt;

import com.pvt.beans.User;
import com.pvt.dao.DAO;
import org.junit.Assert;

public class UserDAOTest {
    public void test_CRUD_User() {
        // create
        int roleManager = 1;
        int roleWorker = 2;

   DAO dao = DAO.getDAO();

        User user = new User();
        user.setLogin("testLogin");
        user.setPassword("testPassword");
        user.setEmail("testEmail@gmail.com");
        user.setFk_role(roleManager);

        boolean id = dao.user.create(user);

        Assert.assertTrue(id);

        // update
        user.setPassword("testPasswordNew");
        user.setEmail("testEmailNew@gmail.com");
        user.setFk_role(roleWorker);

        boolean updated = dao.user.update(user);

        Assert.assertTrue(updated);

        // read
        User updatedUser = dao.user.read(user.getID());

        Assert.assertEquals("testPasswordNew", updatedUser.getPassword());
        Assert.assertEquals("testEmailNew@gmail.com", updatedUser.getEmail());
        Assert.assertEquals(roleWorker, updatedUser.getFk_role());

        // delete
        boolean deleted = dao.user.delete(user);

        Assert.assertTrue(deleted);
    }
}