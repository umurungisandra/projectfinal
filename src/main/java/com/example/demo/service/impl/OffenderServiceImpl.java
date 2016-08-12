package com.example.demo.service.impl;

import com.example.demo.Model.Complaints;
import com.example.demo.Model.Contravention;
import com.example.demo.Model.Police;
import com.example.demo.service.OffenderService;
import com.example.demo.dao.OffenderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sandra on 5/20/2016.
 */
@Service
public class OffenderServiceImpl implements OffenderService {
    @Autowired
    OffenderDao offenderDao;

    @Override
    public void saveOrUpdate(Police offender) {
        offenderDao.save(offender);
    }

    @Override
    public void delete(Police offender) {
        Police us= offenderDao.findOne(offender.getId()) ;

        offenderDao.save(us);
    }

    @Override
    public List<Police> getAll() {
        return offenderDao.findAll();
    }

    @Override
    public List<Police> getBystationPolice(String stationPolice) {


        return offenderDao.findBystationPolice(stationPolice);
    }

    @Override
    public List <Police> getBynumberMatricule(String numberMatricule) {
        return offenderDao.findBynumberMatricule(numberMatricule);
    }

    @Override
    public int countByContraventionAndComplaints(Contravention contravention, Complaints complaints) {
        return 0;
    }
}
