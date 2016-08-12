package com.example.demo.service.impl;

import com.example.demo.Model.Roles;
import com.example.demo.service.RolesService;
import com.example.demo.dao.RolesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sandra on 5/23/2016.
 */
@Service
public class RolesServiceImpl  implements RolesService {
    @Autowired
    RolesDao   rolesDao;

    @Override
    public void saveOrUpdate(Roles roles) {
        rolesDao.save(roles);
    }

    @Override
    public void disableUser(Roles roles) {
        Roles us=rolesDao.findOne(roles.getId());
        us.setVoided(false);
        rolesDao.save(us);
    }

    @Override
    public List<Roles> getAll() {
        return rolesDao.findAll();
    }

    @Override
    public Roles getByroleName(String roleName) {
        return rolesDao.findByroleName(roleName);
    }

    @Override
    public Roles getById(Integer idRole) {
        return rolesDao.findOne(idRole);
    }

    @Override
    public Object findByroleName(String pSearchTerm) {
        return rolesDao.findByroleName(pSearchTerm);
    }
}
