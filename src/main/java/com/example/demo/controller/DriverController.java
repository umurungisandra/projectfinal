package com.example.demo.controller;

import com.example.demo.Model.CurrentUser;
import com.example.demo.Model.Driver;
import com.example.demo.service.DriverService;
import com.example.demo.service.UserService;
import com.example.demo.Model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

/**
 * Created by sandra on 5/29/2016.
 */
@SpringBootApplication

@RestController
public class DriverController {
    @Autowired
    DriverService driverService;
    @Autowired
    UserService userService;
    @RequestMapping(value = "/driver",method = RequestMethod.GET)
    public String getDriverPage(Model model){
        model.addAttribute("driver",new Driver());
        return "driver";
    }
    @RequestMapping(value = "/driver/save",method = RequestMethod.POST)
    public String saveDriver(@Valid @ModelAttribute("driver") Driver driver, Authentication authentication, BindingResult bindingResult, Model model){
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        Users users=userService.getByUsername(currentUser.getUsername()).get();

        driverService.saveOrUpdate(driver);
        model.addAttribute("driver",new Driver());
        return "redirect:/driver";
    }
    @RequestMapping("/driver/id/{id}")
    //public Driver getDriver(@PathVariable("id") String id){
    public Driver getDriver(@PathVariable ("id") Optional<String> drivingLicence) {
       driverService.getBydrivingLisence(drivingLicence.orElse(""));

        RestTemplate restTemplate = new RestTemplate();
        Driver driver = restTemplate.getForObject("http://localhost:9090/driver/id/"+drivingLicence.get(), Driver.class);
        return driver;
    }
}
