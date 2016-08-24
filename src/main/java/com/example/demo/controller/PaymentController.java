package com.example.demo.controller;

import com.example.demo.Model.*;
import com.example.demo.service.ContraventionService;
import com.example.demo.service.PaymentService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by sandra on 5/29/2016.
 */

@RestController
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @Autowired
    UserService userService;
    @Autowired
    ContraventionService contraventionService;

    @RequestMapping(value = "/payment", method = RequestMethod.GET)
    public String getUserPage(Model model) {
        model.addAttribute("user", new Payment());
        return "payment";
    }

    @RequestMapping(value = "/payment/save", method = RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute("payment") Payment payment, Authentication authentication, BindingResult bindingResult, Model model) {
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        Users users = userService.getByUsername(currentUser.getUsername()).get();

        paymentService.saveOrUpdate(payment);
        model.addAttribute("payment", new Payment());
        return "redirect:/payment";
    }

    @RequestMapping(value = "/api/payment/id/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String postPayment(@PathVariable("id") Optional<String> drivingLicense, @RequestBody Payment payment) {
        System.out.println("++++++++++drivingLicense:  " + drivingLicense);
        int amount = getBalance(drivingLicense.get());
        if (amount > payment.getAmountPaid()) {
            return "amount paid are insufficient";
        } else {
            List<Contravention> contraventions = contraventionService.getBydrivingLicense(drivingLicense.get());
            for (Contravention cont : contraventions) {
                cont.setPayment(true);
                contraventionService.saveOrUpdate(cont);
            }
            paymentService.saveOrUpdate(payment);

            return "Completed";
        }
    }
    @RequestMapping(value = "/api/payment/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getPayment(@PathVariable("id") Optional<String> drivingLicense) {
        System.out.println("GET ++++++++++drivingLicense:  " + drivingLicense);
//        int amount = getBalance(drivingLicense.get());
//        if (amount > payment.getAmountPaid()) {
//            return " GET amount paid are insufficient";
//        } else {
            List<Contravention> contraventions = contraventionService.getBydrivingLicense(drivingLicense.get());
            for (Contravention cont : contraventions) {
                cont.setPayment(true);
                contraventionService.saveOrUpdate(cont);
            }
//            paymentService.saveOrUpdate(payment);

            return "Completed";
//        }
    }

    @RequestMapping("/getbalance/{id}")
    public PaymentDriver getAmountDue(@PathVariable("id") String drivingLicense) {
        int amount = getBalance(drivingLicense);
        return new PaymentDriver(drivingLicense, amount);
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
