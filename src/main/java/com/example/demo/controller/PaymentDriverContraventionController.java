package com.example.demo.controller;

import com.example.demo.Model.*;
import com.example.demo.service.ContraventionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by sandra on 8/29/2016.
 */
@Controller
public class PaymentDriverContraventionController {
@Autowired
    ContraventionService contraventionService;
    @RequestMapping( value = "/contravention/list/payment/{id}", method = RequestMethod.GET)
    public String getListPagee(@PathVariable("id") String drivingLicense, Model model) {
        int amount = getBalance(drivingLicense);
        List<Contravention> contraventions=contraventionService.getAll();
        List<PaymentDriverContravention> paymentDriverContravention=new ArrayList<>();
        for(Contravention contr:contraventions) {
            contr.getDrivingLicense();
            contr.getNameDriver();
            contr.getOffenceName();
            contr.isPayment();
            paymentDriverContravention p = new paymentDriverContravention();
            paymentDriverContravention.add("drivingLicense");
            paymentDriverContravention.add("nameDriver");
            paymentDriverContravention.add("offenceName");
            paymentDriverContravention.add('payment');

        }
        model.addAttribute("paymentDriverContravention",new PaymentDriverContravention(drivingLicense , amount));
        return "contraventionlistpayment";
    }
    @RequestMapping(value = "/contravention/list/payment", method = RequestMethod.GET)
    public String getListPagee() {

        return "contraventionlistpayment";
    }
    private int getBalance(String drivingLicense) {
        List<Contravention> contraventions = contraventionService.getBydrivingLicense(drivingLicense);
        int amount = 0;

        for (Contravention contravention : contraventions) {
            double amande = 0;
            Calendar cal = Calendar.getInstance();
            cal.setTime(contravention.getSavedDate());
//            cal.add(Calendar.DATE, 3);
            Date contDate = cal.getTime();
            Date input = new Date();
            LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate cDate = contDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            long diff = Duration.between(cDate.atTime(0, 0), date.atTime(0, 0)).toDays();
            int contrAmount=0;
            System.out.println(diff+"===========================");
            for (Offences offences : contravention.getOffenceName()) {
                Punishment punishment = offences.getPunishments();
                int am=punishment.getOperation().getSetPenalties();
                contrAmount+=am;

            }
            if (diff >3) {
                diff-=3;
                amande = 0.2 * diff*contrAmount;
            }
            amount += amande;
            amount+=contrAmount;
        }
        return amount;
    }


}
