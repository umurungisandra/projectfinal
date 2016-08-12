package com.example.demo.dao;

import com.example.demo.Model.Complaints;
import com.example.demo.Model.Contravention;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by sandra on 5/26/2016.
 */
public interface ComplaintsDao extends JpaRepository<Complaints,Integer> {
    List<Complaints> findBydrivingLicense(Contravention drivingLicense);
}
