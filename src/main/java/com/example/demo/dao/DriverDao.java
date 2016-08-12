package com.example.demo.dao;

import com.example.demo.Model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by sandra on 5/20/2016.
 */
public interface DriverDao extends JpaRepository<Driver,Integer> {

    Optional<Driver> findByDrivingLisence(String drivingLisence);
}