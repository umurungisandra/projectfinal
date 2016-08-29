package com.example.demo.controller;

import com.example.demo.Model.Complaints;
import com.example.demo.Model.Contravention;
import com.example.demo.Model.ContraventionComplaints;
import com.example.demo.service.ComplaintsService;
import com.example.demo.service.ContraventionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.Reference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandra on 8/8/2016.
 */
@RestController
public class ReportController {
    @Autowired
    ContraventionService contraventionService;
    @Autowired
    ComplaintsService complaintsService;
    @RequestMapping(value = "/api/getCount", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<ContraventionComplaints>> getContraventionComplaints(){

        List<ContraventionComplaints> contraventionComplaints =new ArrayList<>();

        for(Complaints complaints:complaintsService.getAll()) {
            for (Contravention contravention : contraventionService.getAll()) {
                contraventionComplaints.add(new ContraventionComplaints(complaints,contravention));
            }
        }
        return new ResponseEntity<>(contraventionComplaints, HttpStatus.OK);
    }




}
