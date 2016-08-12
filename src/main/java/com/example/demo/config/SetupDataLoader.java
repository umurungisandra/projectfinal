package com.example.demo.config;

import com.example.demo.Model.Roles;
import com.example.demo.Model.Users;
import com.example.demo.service.RolesService;
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

    @Autowired
    private RolesService roleService;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (!isAlreadySetup()) {
            return;
        }
            System.out.println(contextRefreshedEvent.getClass().getName()+"============= isAlreadySetup");
            createRoleIfNotFound("ROLE_ADMIN");
            System.out.println(contextRefreshedEvent.getClass().getName()+"=============");
            final Roles adminRole = (Roles) roleService.getByroleName("ROLE_ADMIN");
            final Users user = new Users();
            user.setUsername("Admin");


            user.setPassword(new BCryptPasswordEncoder().encode("demo"));
            Roles role ;


            user.setEnabled(true);
            userService.saveOrUpdate(user);

            alreadySetup = true;

        }

    @Transactional
    private final Roles createRoleIfNotFound(final String name) {

        Roles role =  roleService.getByroleName(name);
        System.out.println(name+"=============++++++++++ isAlreadySetup");
        if (role == null) {
            role = new Roles();
           role.setRoleName(name);
            role.setVoided(false);
            role.setSavedDate(new Date());
            roleService.saveOrUpdate(role);
        }
        return role;
    }

    @Transactional
    private boolean isAlreadySetup() {
        Collection<Users> users= userService.getAll();
        return users.isEmpty();
    }

}
