package com.example.demo.dao;

import com.example.demo.Model.Contravention;
import com.example.demo.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by sandra on 5/20/2016.
 */
public interface PaymentDao extends JpaRepository<Payment,Integer> {
//    List<Payment> findBynumberOfContravention(String numberOfContravention);
    //List<Payment> findBydriver(Driver driver);
    List<Payment> findBydate(Date date);
}
