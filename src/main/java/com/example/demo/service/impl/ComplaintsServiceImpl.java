package com.example.demo.service.impl;

import com.example.demo.Model.Complaints;
import com.example.demo.Model.Contravention;
import com.example.demo.dao.ComplaintsDao;
import com.example.demo.service.ComplaintsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sandra on 5/26/2016.
 */
@Service
public class ComplaintsServiceImpl implements ComplaintsService {
    @Autowired
    ComplaintsDao complaintsDao;

    @Override
    public void saveOrUpdate(Complaints complaints) {
        complaintsDao.save(complaints);
    }

    @Override
    public void delete(Complaints complaints) {
        Complaints us= complaintsDao.findOne(complaints.getId()) ;
        us.setVoided(false);
        complaintsDao.save(us);
    }

    @Override
    public List<Complaints> getAll() {
        return complaintsDao.findAll();
    }

    @Override
    public List<Complaints> getBycontravetion(Contravention drivingLicense) {
        return complaintsDao.findBydrivingLicense(drivingLicense);
    }

    @Override
    public Complaints getById(Integer idComplaints) {
        return complaintsDao.findOne(idComplaints);
    }
}
