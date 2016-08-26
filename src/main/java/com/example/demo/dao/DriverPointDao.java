package com.example.demo.dao;

import com.example.demo.Model.Driver;
import com.example.demo.Model.DriverPoint;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sandra on 8/1/2016.
 */
public interface DriverPointDao extends JpaRepository<DriverPoint, Integer>{
    DriverPoint findByDriverPoint(int point);
    DriverPoint findByFirstName(String firstName);
    DriverPoint findByLastName(String lastName);
}

