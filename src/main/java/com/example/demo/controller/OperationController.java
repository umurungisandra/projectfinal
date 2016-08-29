package com.example.demo.controller;

import com.example.demo.Model.CurrentUser;
import com.example.demo.Model.PunishmentPolicy;
import com.example.demo.Model.Users;
import com.example.demo.service.OperationService;
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
 * Created by sandra on 7/2/2016.
 */
@Controller
public class OperationController {
    @Autowired
    OperationService operationService;
    @Autowired
    UserService userService;
    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(       Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
    }
    @PreAuthorize("hasAnyAuthority('CHIEF_OF_DISTRICT','ADMIN')")
    @RequestMapping(value = "/operation",method = RequestMethod.GET)
    public String getOperationPage(Model model) {
        model.addAttribute("operation", new PunishmentPolicy());
        return "operation";
    }
    @RequestMapping(value = "/operation/save",method = RequestMethod.POST)
    public String saveOperation(@Valid @ModelAttribute("operation") PunishmentPolicy operation, BindingResult bindingResult , Authentication authentication, Model model, RedirectAttributes redirectAttrs){
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldError().getField());

            model.addAttribute("operation", operation);
            return "/operation";
        }
        else {
            CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
            Users users = userService.getByUsername(currentUser.getUsername()).get();
            operation.setSavedBy(users);
            operation.setSavedDate(new Date());
            operationService.saveOrUpdate(operation);
            model.addAttribute("operation", new PunishmentPolicy());
            redirectAttrs.addFlashAttribute("messages", "success");
            Integer idOperation = operation.getId();

            return "redirect:/operation/punishet/" + idOperation;
        }

    }
    @PreAuthorize("hasAnyAuthority('CHIEF_OF_DISTRICT','ADMIN')")
    @RequestMapping(value = "/operation/list", method = RequestMethod.GET)
    public String getListPage(Model model) {
        model.addAttribute("operation", operationService.getAll());
        return "operationList";
    }
    @RequestMapping(value = "/operation/edit/{id}", method = RequestMethod.GET)
    public String getEditPage(@PathVariable String id, Model model) {
        Integer idOperation = Integer.parseInt(id);
        PunishmentPolicy operation = operationService.getById(idOperation);
        model.addAttribute("operation", operation);
        return "operationEdit";
    }
}
