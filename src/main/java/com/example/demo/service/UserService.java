package com.example.demo.service;

import com.example.demo.Model.Users;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by sandra on 5/18/2016.
 */
@Transactional
public interface UserService {
   // @PreAuthorize("hasRole('ADMIN')")
    void saveOrUpdate(Users users);
    //void update(Users users);
    void disableUser(Users users);
    List<Users> getAll();
   Optional<Users> getByUsername(String username);


    Users getById(Integer idUser);
}
