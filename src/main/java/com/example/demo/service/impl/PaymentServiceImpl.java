package com.example.demo.service.impl;

import com.example.demo.Model.Payment;
import com.example.demo.dao.PaymentDao;
import com.example.demo.service.PaymentService;
import com.example.demo.Model.Contravention;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by sandra on 5/20/2016.
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentDao paymentDao;

    @Override
    public void saveOrUpdate(Payment payment) {
paymentDao.save(payment);
    }

    @Override
    public void delete(Payment payment) {
        Payment us= paymentDao.findOne(payment.getId()) ;

        paymentDao.save(us);
    }

//    @Override
//    public List<Payment> getBynumberOfContravention(String numberOfContravention) {
//        return paymentDao.findBynumberOfContravention(numberOfContravention);
//    }

    @Override
    public List<Payment > getBydate(Date date) {
        return paymentDao.findBydate(date);
    }
}
