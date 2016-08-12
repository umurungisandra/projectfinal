package com.example.demo.service.impl;

import com.example.demo.Model.Driver;
import com.example.demo.dao.DriverDao;
import com.example.demo.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by sandra on 5/20/2016.
 */
@Service
public class DriverServiceImpl implements DriverService {
    @Autowired
    DriverDao driverDao;

    @Override
    public void saveOrUpdate(Driver driver) {
     driverDao.save(driver);

    }

    @Override
    public void delete(Driver driver) {

        Driver us= driverDao.findOne(driver.getId()) ;

        driverDao.save(us);
    }


    @Override
    public Optional<Driver> getBydrivingLisence(String drivingLisence) {
        return driverDao.findByDrivingLisence(drivingLisence);
    }
}
