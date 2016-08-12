package com.example.demo.dao;

import com.example.demo.Model.Contravention;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by sandra on 5/20/2016.
 */
public interface ContraventionDao extends JpaRepository<Contravention, Integer> {
    List<Contravention> findBydrivingLicenseAndPayment(String drivingLicense, boolean payment);
}
