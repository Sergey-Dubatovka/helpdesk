package com.pvt;

import com.pvt.beans.User;
import com.pvt.dao.DAO;

import java.util.List;

/**
 * Created by sssergey83 on 16.01.2017.
 */
public class UserService implements IService<User> {
    DAO dao=DAO.getDAO();
    @Override
    public User read(int id) {
        return dao.user.read(id);
    }

    @Override
    public boolean create(User entity) {
        return dao.user.create(entity);
    }

    @Override
    public boolean update(User entity) {
        return dao.user.update(entity);
    }

    @Override
    public boolean delete(User entity) {
        return dao.user.delete(entity);
    }

    @Override
    public List<User> getAll(String WhereAndOrder) {
        return dao.user.getAll(WhereAndOrder);
    }
}
