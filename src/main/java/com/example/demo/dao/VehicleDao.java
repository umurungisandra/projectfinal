package com.example.demo.dao;

import com.example.demo.Model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by sandra on 5/20/2016.
 */
public interface VehicleDao extends JpaRepository<Vehicle,Integer> {
    List<Vehicle> findByplate(String plate);
    List<Vehicle> findBycarteJone(String carteJone);
}
