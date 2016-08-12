package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.Model.CurrentUser;
import com.example.demo.Model.Users;
import com.example.demo.Model.Vehicle;
import com.example.demo.service.VehicleService;
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
public class VehicleController {
    @Autowired
    VehicleService detainedObjectService;
@Autowired
UserService userService;
    @RequestMapping(value = "/detainedObject",method = RequestMethod.GET)
    public String getDetainedObjectPage(Model model){
        model.addAttribute("detainedObject",new Vehicle());
        return "detainedObject";
    }
    @RequestMapping(value = "/detainedObject/save",method = RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute("user") Vehicle detainedObject, Authentication authentication,BindingResult bindingResult, Model model){
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        Users users=userService.getByUsername(currentUser.getUsername()).get();

        detainedObjectService.saveOrUpdate(detainedObject);
        model.addAttribute("detainedObject",new Vehicle());
        return "redirect:/detainedObject";
    }
    @RequestMapping("/vehicle/id/{id}")
    public Vehicle getVehicle(@PathVariable("id") Optional<String> Plate) {
        detainedObjectService.getByplate(Plate.orElse(""));

        RestTemplate restTemplate = new RestTemplate();
        Vehicle vehicle = restTemplate.getForObject("http://localhost:9090/vehicle/id/"+Plate.get(), Vehicle.class);
        return vehicle;
    }
}
