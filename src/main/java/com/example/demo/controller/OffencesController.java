package com.example.demo.controller;

import com.example.demo.Model.CurrentUser;
import com.example.demo.Model.Offences;
import com.example.demo.Model.Punishment;
import com.example.demo.Model.Users;
import com.example.demo.service.OffencesService;
import com.example.demo.service.PunishementService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Date;

/**
 * Created by sandra on 5/29/2016.
 */
@Controller
public class OffencesController   {

    @Autowired
    OffencesService offencesService;
    @Autowired
    PunishementService punishementService;
    @Autowired
    UserService userService;

    @PreAuthorize("hasAnyAuthority('CHIEF_OF_DISTRICT','ADMIN')")
    @RequestMapping(value = "/offences", method = RequestMethod.GET)
    public String getOffencesPage(Model model) {
        model.addAttribute("offences", new Offences());
        model.addAttribute("punishements", punishementService.getAll());
        return "offences";
    }

    @RequestMapping(value = "/offences/save", method = RequestMethod.POST)
    public String saveOffences(@Valid @ModelAttribute("offences") Offences offences,BindingResult bindingResult, Authentication authentication,  Model model, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldError().getField());
            model.addAttribute("offences", offences);
            model.addAttribute("punishements", punishementService.getAll());
            redirectAttrs.addFlashAttribute("messages", "success");
            return "/offences";

        } else {
            CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
            Users users = userService.getByUsername(currentUser.getUsername()).get();
            offences.setSavedBy(users);
            offences.setSavedDate(new Date());
            offencesService.saveOrUpdate(offences);
            model.addAttribute("offences", new Offences());
            model.addAttribute("messages", "unsuccess");
            return "redirect:/offences";
        }
    }
    @PreAuthorize("hasAnyAuthority('CHIEF_OF_DISTRICT','ADMIN')")
    @RequestMapping(value = "/offences/list", method = RequestMethod.GET)
    public String getListPage(Model model) {
        model.addAttribute("offences", offencesService.getAll());
        return "offencesList";
    }

    @PreAuthorize("hasAnyAuthority('CHIEF_OF_DISTRICT','ADMIN')")
    @RequestMapping(value = "/offences/edit/{id}", method = RequestMethod.GET)
    public String getEditPage(@PathVariable String id, Model model) {
        Integer idOffences = Integer.parseInt(id);
        Offences offences = offencesService.getById(idOffences);
        model.addAttribute("offences", offences);
        model.addAttribute("punishements", punishementService.getAll());
        return "offencesEdit";
    }
    @RequestMapping(value = "/punishement/offen/{id}",method = RequestMethod.GET)
    public String getOffenPage(@PathVariable String id, Model model) {
        Integer idPunishement = Integer.parseInt(id);
        Punishment punishment = punishementService.getById(idPunishement);
        model.addAttribute("punishment", punishment);
        Offences offences = new Offences();
        offences.setPunishments(punishment);
        model.addAttribute("offences", offences);
        return "punishementoffence";
    }

}