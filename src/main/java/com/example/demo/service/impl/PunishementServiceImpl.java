package com.example.demo.service.impl;

import com.example.demo.Model.Punishment;
import com.example.demo.dao.PunishementDao;
import com.example.demo.service.PunishementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sandra on 5/20/2016.
 */
@Service
public class PunishementServiceImpl implements PunishementService {
    @Autowired
    PunishementDao punishementDao;

    @Override
    public void saveOrUpdate(Punishment punishement) {
punishementDao.save(punishement);
    }

    @Override
    public void delete(Punishment punishement) {
        Punishment us= punishementDao.findOne(punishement.getId()) ;
        us.setVoided(false);
        punishementDao.save(us);

    }

    @Override
    public List<Punishment> getAll() {
        return punishementDao.findAll();
    }

    @Override
    public List<Punishment> getBydescription(String description) {
        return punishementDao.findBydescription(description);
    }

    @Override
    public Punishment getById(Integer idPunishment) {
        return punishementDao.findOne(idPunishment);
    }
}
