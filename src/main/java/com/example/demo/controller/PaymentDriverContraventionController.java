package com.example.demo.controller;

import com.example.demo.Model.*;
import com.example.demo.service.ContraventionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyAuthority('CHIEF_OF_DISTRICT','CHIEF_OF_STATION','ADMIN')")
    @RequestMapping(value = "/contravention/list/payment", method = RequestMethod.GET)
    public String getListPagee( Model model) {
        List<Contravention> contraventions = contraventionService.getAll();
        List<PaymentDriverContravention> paymentDriverContravention = new ArrayList<>();
        for (Contravention contr : contraventions) {
            contr.getDrivingLicense();
            contr.getNameDriver();
            contr.getOffenceName();
            contr.isPayment();
            int amnt = 0;
            for (Offences offences : contr.getOffenceName()) {
                amnt += offences.getPunishments().getOperation().getSetPenalties();
            }
            PaymentDriverContravention p = new PaymentDriverContravention(contr.getId().toString(), contr.getNameDriver(), amnt, contr.getDrivingLicense(), contr.isPayment());
            paymentDriverContravention.add(p);
        }
System.out.println(paymentDriverContravention.size()+"-------------------------");
        model.addAttribute("paymentDriverContravention", paymentDriverContravention);
        return "contraventionlistpayment";

    }




}
