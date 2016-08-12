package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by sandra on 5/26/2016.
 */
@Controller
public class WelcomeController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHomePage(){
        return "home";
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(){
        return "login";
    }
}
