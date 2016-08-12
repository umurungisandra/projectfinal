package com.example.demo.service;

import com.example.demo.Model.Vehicle;

import java.util.List;

/**
 * Created by sandra on 5/20/2016.
 */
public interface VehicleService {

    void saveOrUpdate(Vehicle detainedObject);

    void delete(Vehicle detainedObject);
   List<Vehicle> getByplate(String plate);
   List<Vehicle> getBycarteJone(String carteJone );

}
