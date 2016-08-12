package com.example.demo.dao;

import com.example.demo.Model.Punishment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by sandra on 5/20/2016.
 */
public interface PunishementDao extends JpaRepository<Punishment,Integer> {
   List<Punishment> findBydescription(String description);
}
