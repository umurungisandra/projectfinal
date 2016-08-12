package com.example.demo.dao;

import com.example.demo.Model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sandra on 7/2/2016.
 */
public interface OperationDao extends JpaRepository<Operation,Integer> {
}
