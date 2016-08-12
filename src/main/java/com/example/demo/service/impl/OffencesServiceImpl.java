package com.example.demo.service.impl;

import com.example.demo.Model.Offences;
import com.example.demo.dao.OffencesDao;
import com.example.demo.service.OffencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sandra on 5/20/2016.
 */
@Service
public class OffencesServiceImpl implements OffencesService {
    @Autowired
    OffencesDao offencesDao;

    @Override
    public void saveOrUpdate(Offences offences) {

        offencesDao.save(offences);
    }

    @Override
    public void delete(Offences offences) {
        Offences us= offencesDao.findOne(offences.getId()) ;
        us.setVoided(false);
        offencesDao.save(us);
    }

    @Override
    public List<Offences> getAll() {
        return offencesDao.findAll();
    }

    @Override
    public List<Offences> getByname(String name) {
        return offencesDao.findAll()  ;
    }

    @Override
    public List<Offences> getBypointMax(int pointMax) {
        return offencesDao.findAll();

    }

    @Override
    public List<Offences> getBypenalties(int penalties) {
        return offencesDao.findAll()  ;
    }

    @Override
    public Offences getById(Integer idOffences) {
        return offencesDao.findOne(idOffences);
    }
// @Override
   // public List<Punishement> getALL() {
       // return offencesDao.findAll();
   // }
}
