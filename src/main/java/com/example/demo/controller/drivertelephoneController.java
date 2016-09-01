package com.example.demo.controller;

import com.example.demo.Model.DriverPoint;
import com.example.demo.service.DriverPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Date;

/**
 * Created by sandra on 8/25/2016.
 */
@Controller
public class drivertelephoneController {
    @Autowired
    DriverPointService driverPointService;

    @RequestMapping(value = "/api/drivertelephone", method = RequestMethod.GET)
    public String getDriverPolicePage(Model model) {
        model.addAttribute("drivertelephone", new DriverPoint());
        return "drivertelephone";
    }

    @RequestMapping(value = "/api/drivertelephone/save", method = RequestMethod.POST)
    public String saveOperation(@Valid @ModelAttribute("drivertelephone") DriverPoint driverPoint, BindingResult bindingResult, Authentication authentication, Model model, RedirectAttributes redirectAttrs) {

            redirectAttrs.addFlashAttribute("messages", "success");
            model.addAttribute("drivertelephone", driverPoint);
            driverPointService.saveOrUpdate(driverPoint);
            model.addAttribute("messages", "unsuccess");
            return "redirect:/drivertelephone";

    }
}