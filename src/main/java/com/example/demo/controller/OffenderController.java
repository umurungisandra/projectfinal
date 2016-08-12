package com.example.demo.controller;

import com.example.demo.Model.Police;
import com.example.demo.service.OffenderService;
import com.example.demo.service.UserService;
import com.example.demo.Model.CurrentUser;
import com.example.demo.Model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Created by sandra on 5/29/2016.
 */
@SpringBootApplication
@RestController
public class OffenderController {
    @Autowired
    OffenderService offenderService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/offender",method = RequestMethod.GET)
    public String getOffenderPage(Model model){
        model.addAttribute("offender",new Police());
        return "offender";
    }
    @RequestMapping(value = "/offender/save",method = RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute("offender") Police offender, Authentication authentication, BindingResult bindingResult, Model model){
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        Users users=userService.getByUsername(currentUser.getUsername()).get();

        offenderService.saveOrUpdate(offender);
        model.addAttribute("offender",new Police());
        return "redirect:/offender";
    }
    @RequestMapping("/police/id/{id}")
    public Police getPoliceOfficer(@PathVariable("id") Optional<String> numberMatricule) {
        offenderService.getBynumberMatricule(numberMatricule.orElse(""));

        RestTemplate restTemplate = new RestTemplate();
        Police policeOfficer = restTemplate.getForObject("http://localhost:9090/police/id/"+numberMatricule.get(), Police.class);
        return policeOfficer;
    }
}
