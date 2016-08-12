package com.example.demo.controller;

import com.example.demo.Model.CurrentUser;
import com.example.demo.Model.Operation;
import com.example.demo.Model.Punishment;
import com.example.demo.Model.Users;
import com.example.demo.service.OperationService;
import com.example.demo.service.PunishementService;
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

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sandra on 5/29/2016.
 */
@Controller
public class PunishementController {
    @Autowired
    PunishementService punishementService;
    @Autowired
    UserService userService;
    @Autowired
    OperationService operationService;
    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(       Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
    }
    @PreAuthorize("hasAnyRole('ROLE_CHIEF_OF_DISTRICT', 'ROLE_ADMIN')")
    @RequestMapping(value = "/punishement",method = RequestMethod.GET)
    public String getPunishementPage(Model model){
        model.addAttribute("punishement",new Punishment());
        model.addAttribute("operation", operationService.getAll());
        return "punishement";
    }
    @RequestMapping(value = "/punishement/save",method = RequestMethod.POST)
    public String savePunishement(@Valid @ModelAttribute("punishement") Punishment punishement,BindingResult bindingResult, Authentication authentication,  Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldError().getField());
            model.addAttribute("punishement", punishement);
            model.addAttribute("operation", operationService.getAll());
            return "/punishement";
        } else {
            CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
            Users users = userService.getByUsername(currentUser.getUsername()).get();
            punishement.setSavedBy(users);
            punishement.setSavedDate(new Date());
            punishementService.saveOrUpdate(punishement);
            model.addAttribute("punishement", new Punishment());
            Integer idPunishement = punishement.getId();
            return "redirect:/punishement/offen/"+idPunishement;
        }
    }
    @PreAuthorize("hasAnyRole('ROLE_CHIEF_OF_DISTRICT', 'ROLE_ADMIN')")
    @RequestMapping(value = "/punishement/list",method = RequestMethod.GET)
    public String getListPage(Model model){
        model.addAttribute("punishement",punishementService.getAll());
        return "punishementlist";
    }
    @RequestMapping(value = "/punishement/edit/{id}", method = RequestMethod.GET)
    public String getEditPage(@PathVariable String id, Model model) {
        Integer idPunishment = Integer.parseInt(id);
        Punishment punishement= punishementService.getById(idPunishment);
        model.addAttribute("punishement",punishement );
        model.addAttribute("operation", operationService.getAll());
        return "punishementEdit";
    }
    @RequestMapping(value = "/operation/punishet/{id}",method = RequestMethod.GET)
    public String getPunishetPage(@PathVariable String id, Model model) {
        Integer idOperation = Integer.parseInt(id);
        Operation operation = operationService.getById(idOperation);
        model.addAttribute("operation", operation);
        Punishment punishment = new Punishment();
        punishment.setOperation(operation);
        model.addAttribute("punishement", punishment);
        return "operationpunishement";
    }
}
