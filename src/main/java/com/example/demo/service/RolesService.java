package com.example.demo.service;

import com.example.demo.Model.Roles;

import java.util.List;

/**
 * Created by sandra on 5/23/2016.
 */
public interface RolesService {
    void saveOrUpdate(Roles roles);
    //void update(Users users);
    void disableUser(Roles roles);
    List<Roles> getAll();
   Roles getByroleName(String roleName);
Roles getById(Integer idRole);

    Object findByroleName(String pSearchTerm);
}
