package com.example.demo.service;

import com.example.demo.Model.Driver;

import java.util.Optional;

/**
 * Created by sandra on 5/20/2016.
 */
public interface DriverService {
    void saveOrUpdate(Driver driver);
    void delete(Driver driver);
   // Driver getByfirstname(String firstname);
   // Driver getBylastname(String lastname);
   Optional<Driver> getBydrivingLisence(String drivingLisence);




}
