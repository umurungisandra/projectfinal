package com.example.demo.controller;

import com.example.demo.Model.Complaints;
import com.example.demo.Model.Contravention;
import com.example.demo.Model.CurrentUser;
import com.example.demo.Model.Users;
import com.example.demo.service.ComplaintsService;
import com.example.demo.service.ContraventionService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sandra on 6/14/2016.
 */
@Controller
public class ComplaintsController {

    @Autowired
    ComplaintsService complaintsService;
    @Autowired
    UserService userService;
    @Autowired
    ContraventionService contraventionService;
    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(       Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
    }
    @PreAuthorize("hasAnyAuthority('CHIEF_OF_DISTRICT','CHIEF_OF_STATION','ADMIN')")
    @RequestMapping(value = "/complaints", method = RequestMethod.GET)
    public String getComplaintsPage(Model model) {
        model.addAttribute("complaints", new Complaints());
        model.addAttribute("contravention",contraventionService.getAll());
        return "complaints";
    }
    @RequestMapping(value = "/complaints/save", method = RequestMethod.POST)
    public String saveComplaints(@Valid @ModelAttribute("complaints") Complaints complaints,BindingResult bindingResult, Authentication authentication,Model model,RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldError().getField());
            model.addAttribute("complaints", complaints);
            return "/complaints";
        } else {
            CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
            Users users = userService.getByUsername(currentUser.getUsername()).get();
            complaints.setSavedBy(users);
            complaints.setSavedDate(new Date());
            complaintsService.saveOrUpdate(complaints);
            model.addAttribute("complaints", new Complaints());
            redirectAttrs.addFlashAttribute("messages", "success");
            return "redirect:/complaints";
        }

    }
    @PreAuthorize("hasAnyAuthority('CHIEF_OF_DISTRICT','CHIEF_OF_STATION','ADMIN')")
    @RequestMapping(value = "/complaints/list", method = RequestMethod.GET)
    public String getListPage(Model model) {
        model.addAttribute("complaints", complaintsService.getAll());
        return "complaintslist";
    }
    @PreAuthorize("hasAnyAuthority('CHIEF_OF_DISTRICT','CHIEF_OF_STATION','ADMIN')")
    @RequestMapping(value = "/complaints/edit/{id}", method = RequestMethod.GET)
    public String getEditPage(@PathVariable String id, Model model) {
        Integer idComplaints = Integer.parseInt(id);
        Complaints complaints = complaintsService.getById(idComplaints);
        model.addAttribute("complaints", complaints);

        return "complaintsEdit";
    }
}