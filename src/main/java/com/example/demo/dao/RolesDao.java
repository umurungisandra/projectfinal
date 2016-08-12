package com.example.demo.dao;

import com.example.demo.Model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by sandra on 5/23/2016.
 */
public interface RolesDao extends JpaRepository<Roles,Integer > {

    Roles findByroleName(String roleName);
}