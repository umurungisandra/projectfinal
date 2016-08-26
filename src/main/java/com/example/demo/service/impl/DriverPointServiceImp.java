package com.example.demo.service.impl;

import com.example.demo.Model.DriverPoint;
import com.example.demo.dao.DriverPointDao;
import com.example.demo.service.DriverPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by sandra on 8/1/2016.
 */
@Service
public class DriverPointServiceImp implements DriverPointService{
    @Autowired
    DriverPointDao driverPointDao;

    @Override
    public void saveOrUpdate(DriverPoint driverPoint) {
        driverPointDao.save (driverPoint);
    }

    @Override
    public void delete(DriverPoint driverPoint) {
        DriverPoint us= driverPointDao.findOne(driverPoint.getId()) ;
        us.setVoided(false);
        driverPointDao.save(us);
    }

    @Override
    public List<DriverPoint> getAll() {
        return driverPointDao.findAll();
    }

    @Override
    public DriverPoint getBypoint(int point) {
        return driverPointDao.findByDriverPoint(point);
    }

    @Override
    public DriverPoint getByfirstName(String firstName) {
        return driverPointDao.findByFirstName(firstName);
    }

    @Override
    public DriverPoint getBylastName(String lastName) {
        return driverPointDao.findByLastName(lastName);
    }

    @Override
    public Optional<DriverPoint> getBydrivingLisence(String drivingLisence) {
        List<DriverPoint> driverPoints=getAll();
        for(DriverPoint d:driverPoints){
            if(d.getDriver().getDrivingLisence().equalsIgnoreCase(drivingLisence)){
                return Optional.of(d);
            }
        }
        return null;
    }
}
