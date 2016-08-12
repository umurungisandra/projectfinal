package com.example.demo.service;

import com.example.demo.Model.Complaints;
import com.example.demo.Model.Contravention;

import java.util.List;

/**
 * Created by sandra on 5/26/2016.
 */
public interface ComplaintsService {
    void saveOrUpdate(Complaints complaints);
    void delete(Complaints complaints);
    List<Complaints> getAll();
   List<Complaints> getBycontravetion(Contravention drivingLicense);
    Complaints  getById(Integer idComplaints);
}
