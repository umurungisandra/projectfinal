package com.example.demo.service.impl;

import com.example.demo.Model.CurrentUser;
import com.example.demo.Model.Users;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by sandra on 6/7/2016.
 */
@Service
public class CurrentUserServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;


    @Override
    public CurrentUser loadUserByUsername(String s) throws UsernameNotFoundException {
        Users users = userService.getByUsername(s).orElseThrow(() -> new UsernameNotFoundException(String.format("Username not found ", s)));
        CurrentUser currentUser= new CurrentUser(users);
        System.out.println(currentUser.getAuthorities().toString());

        return currentUser;
    }
}

