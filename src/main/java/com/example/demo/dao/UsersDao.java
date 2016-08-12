package com.example.demo.dao;

import com.example.demo.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by sandra on 5/18/2016.
 */
public interface UsersDao extends JpaRepository<Users, Integer> {
    Optional<Users> findByUsername(String username);

}
