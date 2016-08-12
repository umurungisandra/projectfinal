package com.example.demo.service;

import com.example.demo.Model.Punishment;

import java.util.List;

/**
 * Created by sandra on 5/20/2016.
 */
public interface PunishementService {
    void saveOrUpdate(Punishment punishement);
    void delete(Punishment punishement);
    List<Punishment> getAll();
    List<Punishment> getBydescription(String description);
    Punishment getById(Integer idPunishment);
}
