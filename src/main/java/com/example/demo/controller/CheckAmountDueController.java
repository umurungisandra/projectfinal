package com.example.demo.controller;

import com.example.demo.Model.*;
import com.example.demo.service.ContraventionService;
import com.example.demo.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by sandra on 8/2/2016.
 */
@Controller
public class CheckAmountDueController {

    @Autowired
    ContraventionService contraventionService;
    @Autowired
    DriverService driverService;


    @PreAuthorize("hasAnyAuthority('CHIEF_OF_DISTRICT','CHIEF_OF_STATION','ADMIN','DIRECTEUR_EXHIBITS_AND_FINES')")
    @RequestMapping( value = "/checkbalance/{id}", method = RequestMethod.GET)
    public String getCheckBalance(@PathVariable("id") String drivingLicense, Model model) {
        int amount = getBalance(drivingLicense);
        Driver driver=driverService.getBydrivingLisence(drivingLicense).get();
        System.out.println(driver.getFirstName()+" "+driver.getLastName());
        model.addAttribute("paymentDriver",new PaymentDriver(drivingLicense,driver, amount));
        return "getbalance";
    }
    @RequestMapping(value = "/getbalance", method = RequestMethod.GET)
    public String getCheckBalancePage() {

        return "getbalance";
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
