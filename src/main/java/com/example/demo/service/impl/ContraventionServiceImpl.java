package com.example.demo.service.impl;

import com.example.demo.Model.Complaints;
import com.example.demo.dao.ContraventionDao;
import com.example.demo.service.ContraventionService;
import com.example.demo.Model.Contravention;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by sandra on 5/20/2016.
 */
@Service
public class ContraventionServiceImpl implements ContraventionService {

    @Autowired
    ContraventionDao contraventionDao;

    @Override
    public void saveOrUpdate(Contravention contravention) {
        contraventionDao.save(contravention);
    }

    @Override
    public void delete(Contravention contravention) {
        Contravention us= contraventionDao.findOne(contravention.getId()) ;
        us.setVoided(false);
        contraventionDao.save(us);

    }

    @Override
    public List<Contravention> getAll() {
       // contraventionDeo.findAll();
        return contraventionDao.findAll();

    }

    @Override
    public Contravention getById(Integer idContravention) {
        return contraventionDao.findOne(idContravention);
    }

    @Override
    public List<Contravention> getBydrivingLicense(String drivingLicense) {
        return contraventionDao.findBydrivingLicenseAndPayment(drivingLicense,false);
    }

    @Override
    public int countByContraventionAndComplaints(Contravention contravention, Complaints complaints) {
        return 0;
    }

    @Override
    public List<Contravention> getByDateBetween(Date start, Date end) {
        return contraventionDao.findBySavedDateBetween(new java.sql.Date(start.getTime()),new java.sql.Date(end.getTime()));
    }
}
