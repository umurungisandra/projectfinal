package com.example.demo.controller;

import com.example.demo.Model.Complaints;
import com.example.demo.Model.Contravention;
import com.example.demo.Model.ContraventionComplaints;
import com.example.demo.service.ComplaintsService;
import com.example.demo.service.ContraventionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandra on 8/29/2016.
 */
@Controller
public class ContraventionComplaintController {

    @Autowired
    ComplaintsService complaintsService;
    private Object contraventionComplaints;

    @RequestMapping(value = "/contraventionComplaints", method = RequestMethod.GET)
    public String getContraventionComplaintsPage(Model model) {


        List<Complaints> complaints = complaintsService.getAll();
        List<ContraventionComplaints> contaventionComplaints = new ArrayList<>();

        for (Complaints complaint : complaints) {
            complaint.getComplaint();
            complaint.getDecision();
            complaint.getResponsible();
            complaint.getDrivingLicense().getNameDriver();
            complaint.getDrivingLicense().getDrivingLicense();
            complaint.getDrivingLicense().getPlateNumber();
           // complaint.getDrivingLicense().getOffenceName();
            complaint.getDrivingLicense().getSavedDate();
            ContraventionComplaints p = new ContraventionComplaints(complaint.getId().toString(), complaint.getComplaint(), complaint.getDecision(), complaint.getResponsible(), complaint.getDrivingLicense().getNameDriver(), complaint.getDrivingLicense().getDrivingLicense(), complaint.getDrivingLicense().getPlateNumber(), complaint.getDrivingLicense().getSavedDate());
            contaventionComplaints.add(p);
        }
        model.addAttribute("contraventionComplaints", contaventionComplaints);
        return "contraventionComplaints";
    }


}
