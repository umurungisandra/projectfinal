package com.example.demo.dao;

import com.example.demo.Model.Offences;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sandra on 5/20/2016.
 */
public interface OffencesDao extends JpaRepository<Offences,Integer> {
    //List<Offences>findALL();
}
