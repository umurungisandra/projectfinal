package com.example.demo.config;

import com.example.demo.Model.Roles;
import com.example.demo.Model.Users;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sandra on 7/11/2016.
 */
@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private boolean alreadySetup = false;
    @Autowired
    private UserService userService;


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (!isAlreadySetup()) {
            return;
        }

        createRoleIfNotFound("Admin");

        final Roles adminRole = Roles.ADMIN;
        final Users user = new Users();
        user.setUsername("admin");
        String pass = "demo";
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        user.setPassword(b.encode(pass));
        user.setSavedDate(new Date());
        user.setFirstName("firstaName");
        user.setLastName("lastName");
        user.setDistrict("district");
        user.setSector("sector");
        //user.setCell("cell");
        //user.setVillage("village");
        user.setNumberMatricule("numbermatricule");
        user.setProvince("province");
        user.setPost("post");
        user.setEnabled(true);
        user.setRole(adminRole);
        userService.saveOrUpdate(user);

        alreadySetup = true;

        }
    @Transactional
    private final Roles createRoleIfNotFound(final String name) {
        return Roles.valueOf(name);

    }
    @Transactional
    private boolean isAlreadySetup() {
        Collection<Users> users= userService.getAll();
        return users.isEmpty();
    }

}
