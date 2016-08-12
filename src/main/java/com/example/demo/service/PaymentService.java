package com.example.demo.service;

import com.example.demo.Model.Contravention;
import com.example.demo.Model.Payment;

import java.util.Date;
import java.util.List;

/**
 * Created by sandra on 5/20/2016.
 */
public interface PaymentService {
    void saveOrUpdate(Payment payment);
    void delete(Payment payment);
//    List<Payment> getBynumberOfContravention(String numberOfContravention);
    //List<Payment> getBydriver(Driver driver);
   List< Payment> getBydate(Date date);

}
