package com.example.demo.dao;

import com.example.demo.Model.Police;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by sandra on 5/20/2016.
 */
public interface OffenderDao extends JpaRepository<Police,Integer> {
    List<Police> findBystationPolice(String stationPolice);
    List<Police> findBynumberMatricule(String numberMatricule);
}
