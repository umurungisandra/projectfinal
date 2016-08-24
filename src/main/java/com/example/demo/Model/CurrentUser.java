package com.example.demo.Model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by sandra on 6/6/2016.
 */
public class CurrentUser implements UserDetails {
    private Users users;

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public CurrentUser(Users users) {
        this.users = users;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getAuthorities(users);
    }

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return users.isEnabled();
    }

    private Collection<GrantedAuthority> getAuthorities(Users users) {
        List<GrantedAuthority> authList = getGrantedAuthorities(users.getRole());
        return authList;
    }

    private List<GrantedAuthority> getGrantedAuthorities(
            Roles role) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority(role.name()));

        return authorities;
    }
}
