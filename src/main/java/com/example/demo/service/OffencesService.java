package com.example.demo.service;

import com.example.demo.Model.Offences;

import java.util.List;

/**
 * Created by sandra on 5/20/2016.
 */
public interface OffencesService {
    void saveOrUpdate(Offences offences);
    void delete (Offences offences);
    List<Offences> getAll();
    List < Offences> getByname(String name);
   List< Offences> getBypointMax(int pointMax);
   List< Offences> getBypenalties(int penalties);
    Offences  getById(Integer idOffences);
   //List<Punishement> getALL();

}
