package com.example.demo.service;

import com.example.demo.Model.Operation;

import java.util.List;

/**
 * Created by sandra on 7/2/2016.
 */
public interface OperationService {
    void saveOrUpdate(Operation operation);
    void delete(Operation operation);
    List<Operation> getAll();
    //List<Operation> getBydescription(String description);
    Operation getById(Integer idOperation);
}
