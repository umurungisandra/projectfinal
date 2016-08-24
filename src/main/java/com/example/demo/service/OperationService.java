package com.example.demo.service;

import com.example.demo.Model.PunishmentPolicy;

import java.util.List;

/**
 * Created by sandra on 7/2/2016.
 */
public interface OperationService {
    void saveOrUpdate(PunishmentPolicy operation);
    void delete(PunishmentPolicy operation);
    List<PunishmentPolicy> getAll();
    //List<Operation> getBydescription(String description);
    PunishmentPolicy getById(Integer idOperation);
}
