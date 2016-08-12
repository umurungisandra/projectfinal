package com.example.demo.controller;

import com.example.demo.Model.CurrentUser;
import com.example.demo.Model.Roles;
import com.example.demo.Model.Users;
import com.example.demo.service.RolesService;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sandra on 5/29/2016.
 */
@Controller
public class RolesController {
    @Autowired
    RolesService rolesService;
    @Autowired
    UserService userService;
    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(       Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
    }
    @PreAuthorize("hasRole( 'ROLE_ADMIN')")
    @RequestMapping(value = "/roles ", method = RequestMethod.GET)
    public String getRolesPage(Model model) {
        model.addAttribute("roles", new Roles());
        return "roles";
    }

    @RequestMapping(value = "/roles/save", method = RequestMethod.POST)
    public String saveRoles(@Valid @ModelAttribute("roles") Roles roles, BindingResult bindingResult, Authentication authentication,  Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldError().getField());
            model.addAttribute("roles", roles);
            return "/roles";
        } else {
            CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
            Users users = userService.getByUsername(currentUser.getUsername()).get();
            roles.setSavedBy(users);
            roles.setSavedDate(new Date());
            rolesService.saveOrUpdate(roles);
            model.addAttribute("roles", new Roles());
            return "redirect:/roles";
        }
    }
    @PreAuthorize("hasRole( 'ROLE_ADMIN')")
    @RequestMapping(value = "/roles/list", method = RequestMethod.GET)
    public String getListPage(Model model) {
        model.addAttribute("roles", rolesService.getAll());
        return "roleslist";
    }

    @RequestMapping(value = "/search")
    public String Search(@RequestParam("searchString") String searchString, Model model) {

        if(searchString != null){
            Object searchResult = rolesService.findByroleName(searchString);
            model.addAttribute("searchResult", searchResult);
        }

        return "search";
    }

    @RequestMapping(value = "/roles/edit/{id}", method = RequestMethod.GET)
    public String getEditPage(@PathVariable String id, Model model) {
        Integer idRole = Integer.parseInt(id);
        Roles roles= rolesService.getById(idRole);
        model.addAttribute("roles", roles);
        return "rolesEdit";
    }
}
