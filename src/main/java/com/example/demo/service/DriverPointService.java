package com.example.demo.service;

import com.example.demo.Model.DriverPoint;

import java.util.List;
import java.util.Optional;

/**
 * Created by sandra on 8/1/2016.
 */
public interface DriverPointService {
    void saveOrUpdate(DriverPoint driverPoint);
    void delete(DriverPoint driverPoint);
    List<DriverPoint> getAll();
    DriverPoint getBypoint(int point);
    Optional<DriverPoint> getBydrivingLisence(String drivingLisence);
}
