package com.example.demo.controller;

import com.example.demo.Model.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by sandra on 5/29/2016.
 */
@Controller

public class ContraventionController {
    @Autowired
    ContraventionService contraventionService;
    @Autowired
    UserService userService;
    @Autowired
    OffencesService offencesService;
    @Autowired
    DriverPointService driverPointService;

    @PreAuthorize("hasAnyAuthority('POLICE_OFFICER')")
    @RequestMapping(value = "/contravention", method = RequestMethod.GET)
    public String getContraventionPage(Model model) {
        model.addAttribute("contravention", new Contravention());
        model.addAttribute("offences", offencesService.getAll());
        return "contravention";
    }

    @RequestMapping(value = "/contravention/save", method = RequestMethod.POST)
    public String saveContravention(@Valid @ModelAttribute("contravention") Contravention contravention, BindingResult bindingResult, Authentication authentication, Model model, RedirectAttributes redirectAttrs) {

        if (contravention.getDrivingLicense() != null) {
            if (!checkDriverExist(contravention.getDrivingLicense())) {
                System.out.println(bindingResult.getFieldError().getField());
                model.addAttribute("contravention", contravention);
                redirectAttrs.addFlashAttribute("messages", "success");
                return "/contravention";
            }

            CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
            Users users = userService.getByUsername(currentUser.getUsername()).get();
            contravention.setSavedBy(users);
            contravention.setSavedDate(new Date());
            if (soustractPoint(getDriverByDrivingLicense(contravention.getDrivingLicense()), new ArrayList<>(contravention.getOffenceName()))) {

            }
            contraventionService.saveOrUpdate(contravention);

            model.addAttribute("contravention", new Contravention());
            redirectAttrs.addFlashAttribute("messages", "success");
            model.addAttribute("offences", offencesService.getAll());

            return "redirect:/contravention";

        } else if (contravention.getPlateNumber() != null) {
            if (!checkVehicleExist(contravention.getPlateNumber())) {
                System.out.println(bindingResult.getFieldError().getField());
                model.addAttribute("contravention", contravention);
                return "/contravention";
            }

            CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
            Users users = userService.getByUsername(currentUser.getUsername()).get();
            contravention.setSavedBy(users);
            contravention.setSavedDate(new Date());
            contraventionService.saveOrUpdate(contravention);
            model.addAttribute("contravention", new Contravention());
            model.addAttribute("offences", offencesService.getAll());

            return "redirect:/contravention";

        } else {
            System.out.println(bindingResult.getFieldError().getField());
            model.addAttribute("contravention", contravention);
            return "/contravention";
        }
    }

    //contravention report  parameters
    @RequestMapping(value = {"/contravention/report"}, method = RequestMethod.GET)
    public String reportPage(Model model, HttpServletRequest request) {
        return "contraventionreport";
    }

    @RequestMapping(value = {"/contravention/report"}, method = RequestMethod.POST)
    public String listPage(Model model, HttpServletRequest request) {


        try {

            String dateString = request.getParameter("startdate");
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date startdate = format.parse(dateString);

            String enddateString = request.getParameter("enddate");
            Date enddate = format.parse(enddateString);
            System.out.println("From " + dateString + " To " + enddateString + " Before ");
            List<Contravention> dataEntryReports = contraventionService.getByDateBetween(startdate, enddate);

            System.out.println("From " + dateString + " To " + enddateString + " Found " + dataEntryReports.size());

            model.addAttribute("contravention", dataEntryReports);
            return "Contraventionlist";
        } catch (ParseException e) {
            return "redirect:/contraventionreport";
        }
    }

    @PreAuthorize("hasAnyAuthority('CHIEF_OF_DISTRICT','CHIEF_OF_STATION','ADMIN')")
    @RequestMapping(value = "/contravention/list", method = RequestMethod.GET)
    public String getListPage(Model model) {
        model.addAttribute("contravention", contraventionService.getAll());

        return "contraventionlist";
    }


    private Driver getDriverByDrivingLicense(String drivingLicense) {
        RestTemplate restTemplate = new RestTemplate();
        if (checkDriverExist(drivingLicense)) {
            return restTemplate.getForObject("http://localhost:9090/driver/id/" + drivingLicense, Driver.class);
        }
        return null;
    }

    private boolean checkDriverExist(String drivingLicense) {
        Driver driver = null;
        RestTemplate restTemplate = new RestTemplate();
        try {
            driver = restTemplate.getForObject("http://localhost:9090/driver/id/" + drivingLicense, Driver.class);
            checkNotNull(driver);

            return true;
        } catch (NullPointerException ex) {
            return false;
        }
    }

    private boolean checkVehicleExist(String plate) {
        Vehicle vehicle = null;
        RestTemplate restTemplate = new RestTemplate();
        try {
            vehicle = restTemplate.getForObject("http://localhost:9090/vehicle/id/" + plate, Vehicle.class);
            checkNotNull(vehicle);

            return true;
        } catch (NullPointerException ex) {
            return false;
        }
    }

    @PreAuthorize("hasAnyAuthority('POLICE_OFFICER','CHIEF_OF_STATION','CHIEF_OF_DISTRICT','ADMIN')")
    @RequestMapping(value = "/getpoint/{id}", method = RequestMethod.GET)
    public String getPoint(@PathVariable("id") String drivingLicense, Model model) {
        Optional<DriverPoint> driverPoint = driverPointService.getBydrivingLisence(drivingLicense);
        DriverPoint driverPoint1 = new DriverPoint();
        try {
            checkNotNull(driverPoint);

            if (driverPoint.isPresent()) {
                driverPoint1 = driverPoint.get();
            }
            model.addAttribute("driverPoint", driverPoint1);
        } catch (NullPointerException e) {

        }
        return "chekpoint";
    }

    @RequestMapping(value = "/getpoint", method = RequestMethod.GET)
    public String getCheckPoint() {

        return "chekpoint";
    }

    private boolean soustractPoint(Driver driver, List<Offences> offences) {
        Optional<DriverPoint> driverPoint = driverPointService.getBydrivingLisence(driver.getDrivingLisence());
        DriverPoint driverPoint1 = new DriverPoint();
        try {
            checkNotNull(driverPoint);

            if (driverPoint.isPresent()) {
                driverPoint1 = driverPoint.get();
                int point = driverPoint1.getDriverPoint();
                for (Offences offen : offences) {
                    Punishment punishment = offen.getPunishments();
                    point = point - punishment.getOperation().getSetMark();
                }

                driverPoint1.setDriverPoint(point);
                driverPointService.saveOrUpdate(driverPoint1);
                return true;
            } else {
                driverPoint1.setDriverPoint(100);
                driverPoint1.setDriver(driver);
                driverPointService.saveOrUpdate(driverPoint1);
                return soustractPoint(driverPoint1.getDriver(), offences);
            }
        } catch (NullPointerException n) {
            driverPoint1 = new DriverPoint();
            driverPoint1.setDriverPoint(100);
            driverPoint1.setDriver(driver);
            driverPointService.saveOrUpdate(driverPoint1);
            return soustractPoint(driverPoint1.getDriver(), offences);
        }
    }

}
