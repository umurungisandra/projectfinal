package com.example.demo.controller;

import com.example.demo.Model.CurrentUser;
import com.example.demo.Model.Police;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by sandra on 5/26/2016.
 */
@Controller
public class UsersController {
    @Autowired
    UserService userService;
    @Autowired
    RolesService rolesService;
    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(       Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
    }
    @PreAuthorize("hasAnyRole('ROLE_CHIEF_OF_DISTRICT','ROLE_ADMIN')")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUserPage(Model model) {
        model.addAttribute("user", new Users());
        model.addAttribute("role", rolesService.getAll());
        return "users";
    }
    @RequestMapping(value = "/users/save", method = RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute("user") Users users, BindingResult bindingResult, Authentication authentication, Model model) {
        if (!bindingResult.hasErrors()) {
            if (users.getNumberMatricule() != null) {
                if (!checkPoliceExist(users.getNumberMatricule())) {

                    model.addAttribute("users", users);
                    return "/users";
                }
                CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
                Users user = userService.getByUsername(currentUser.getUsername()).get();
                users.setSavedBy(user);
                users.setSavedDate(new Date());
                String sector = users.getSector();
                sector = sector.split(",")[0];
                users.setSector(sector);
                String cell = users.getCell();
                cell = cell.split(",")[0];
                users.setCell(cell);
                String village = users.getVillage();
                village = village.split(",")[0];
                users.setVillage(village);
                userService.saveOrUpdate(users);
                model.addAttribute("user", new Users());
                return "redirect:/users";


            } else {

                model.addAttribute("users", users);
                return "/users";
            }
        }

     else {

        model.addAttribute("users", users);
        return "/users";
    }

  }
    @RequestMapping(value = "/users/list", method = RequestMethod.GET)
    public String getListPage(Model model) {
        model.addAttribute("users", userService.getAll());
        return "userlist";
    }

    @RequestMapping(value = "/users/edit/{id}", method = RequestMethod.GET)
    public String getEditPage(@PathVariable String id, Model model) {
        Integer idUser = Integer.parseInt(id);
        Users users = userService.getById(idUser);
        model.addAttribute("user", users);
        model.addAttribute("roles", rolesService.getAll());
        return "userEdit";
    }

    private boolean checkPoliceExist(String numberMatricule){
        Police police = null;
        RestTemplate restTemplate = new RestTemplate();
        try {
            police = restTemplate.getForObject("http://localhost:9090/police/id/" +numberMatricule, Police.class);
            checkNotNull(police);

            return true;
        } catch (NullPointerException ex) {
            return false;
        }
    }


}
