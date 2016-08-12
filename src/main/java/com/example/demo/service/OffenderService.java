package com.example.demo.service;

import com.example.demo.Model.Complaints;
import com.example.demo.Model.Contravention;
import com.example.demo.Model.Police;

import java.util.List;

/**
 * Created by sandra on 5/20/2016.
 */
public interface OffenderService {
    void saveOrUpdate(Police offender);
    void delete (Police offender);
    //OffenderService getByfirstName(String firstName);
    //OffenderService getBylastName(String lastName);
    List<Police> getAll();
    List<Police> getBystationPolice(String stationPolice);
    List<Police> getBynumberMatricule(String numberMatricule );
    int countByContraventionAndComplaints(Contravention contravention, Complaints complaints);

}
