package com.example.ttest.service;

import com.example.ttest.model.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private String userPassword;
    private String userName;
    private UserEntity user;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(UserEntity user, Collection<? extends GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    //先寫死的版本
//    public CustomUserDetails(String userName,String userPassword, Collection<? extends GrantedAuthority> authorities) {
//        this.userName = userName;
//        this.userPassword = userPassword;
//        this.authorities = authorities;
//    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getUserPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
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
        return true;
    }


}
