package com.pvt;

import com.pvt.beans.Ad;
import com.pvt.dao.AdDAO;
import com.pvt.dao.DAO;

import java.util.List;

/**
 * Created by sssergey83 on 16.01.2017.
 */
public class AdService implements IService<Ad> {

    private DAO dao = DAO.getDAO();

    @Override
    public Ad read(int id) {
        return dao.ad.read(id);
    }

    @Override
    public boolean create(Ad entity) {
        return dao.ad.create(entity);
    }

    @Override
    public boolean update(Ad entity) {
        return dao.ad.update(entity);
    }

    @Override
    public boolean delete(Ad entity) {
        return dao.ad.delete(entity);
    }

    @Override
    public List<Ad> getAll(String WhereAndOrder) {
        return dao.ad.getAll(WhereAndOrder);
    }
}
