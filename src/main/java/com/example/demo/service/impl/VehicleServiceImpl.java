package com.example.demo.service.impl;

import com.example.demo.Model.Vehicle;
import com.example.demo.service.VehicleService;
import com.example.demo.dao.VehicleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sandra on 5/20/2016.
 */
@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    VehicleDao detainedObjectDao;

    @Override
    public void saveOrUpdate(Vehicle detainedObject) {
        detainedObjectDao.save(detainedObject);
    }

    @Override
    public void delete(Vehicle detainedObject) {
        Vehicle us= detainedObjectDao.findOne(detainedObject.getId()) ;

        detainedObjectDao.save(us);
    }

    @Override
    public List<Vehicle> getByplate(String plate) {
        return detainedObjectDao.findByplate(plate);
    }

    @Override
    public List<Vehicle>getBycarteJone(String carteJone) {
        return detainedObjectDao.findBycarteJone(carteJone);
    }
}
