package com.example.demo.service.impl;

import com.example.demo.Model.PunishmentPolicy;
import com.example.demo.dao.OperationDao;
import com.example.demo.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sandra on 7/2/2016.
 */
@Service
public class OperationServiceImp implements OperationService {
    @Autowired
    OperationDao operationDao;
    @Override
    public void saveOrUpdate(PunishmentPolicy operation) {
        operationDao.save(operation);

    }

    @Override
    public void delete(PunishmentPolicy operation) {
        PunishmentPolicy us= operationDao.findOne(operation.getId()) ;
        us.setVoided(false);
        operationDao.save(us);
    }

    @Override
    public List<PunishmentPolicy> getAll() {
        return operationDao.findAll();
    }

    @Override
    public PunishmentPolicy getById(Integer idOperation) {
        return operationDao.findOne(idOperation);
    }
}
