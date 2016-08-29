package com.example.demo.controller;

import com.example.demo.Model.ContraventionComplaints;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by sandra on 8/29/2016.
 */
@Controller
public class ContraventionComplaintController {
    @RequestMapping(value = "/contraventionComplaints", method = RequestMethod.GET)
    public String getContraventionComplaintsPage(Model model) {

        model.addAttribute("contraventionComplaints",new ContraventionComplaints());
        return "contraventionComplaints";
    }
}
