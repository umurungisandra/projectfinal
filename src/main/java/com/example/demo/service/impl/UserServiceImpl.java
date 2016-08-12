package com.example.demo.service.impl;

import com.example.demo.Model.Users;
import com.example.demo.dao.UsersDao;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by sandra on 5/18/2016.
 */
@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    UsersDao usersDao;

    @Override
    public void saveOrUpdate(Users users) {
        String pass = users.getPassword();
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        users.setPassword(b.encode(pass));
        usersDao.save(users);
    }


    @Override
    public void disableUser(Users users) {
        Users us = usersDao.findOne(users.getId());
        us.setEnabled(false);
        usersDao.save(us);
    }

    @Override
    public List<Users> getAll() {
        return usersDao.findAll();
    }

    @Override
    public Optional<Users> getByUsername(String username) {
        return usersDao.findByUsername(username);
    }

    @Override
    public Users getById(Integer idUser) {
        return usersDao.findOne(idUser);
    }

}