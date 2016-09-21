package com.example.demo.service;

import com.example.demo.Model.Complaints;
import com.example.demo.Model.Contravention;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by sandra on 5/18/2016.
 */
public interface ContraventionService {
    void saveOrUpdate(Contravention contravention);
    void delete(Contravention contravention);
    List<Contravention> getAll();
    Contravention  getById(Integer idContravention);
    List<Contravention> getBydrivingLicense(String drivingLicense);
    List<Contravention> getByDateBetween(Date start, Date end);

    int countByContraventionAndComplaints(Contravention contravention, Complaints complaints);
}
