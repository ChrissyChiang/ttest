package com.example.ttest.controller;

import com.example.ttest.entity.UserEntity;
import com.example.ttest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")
//    @PreAuthorize("hasAnyAuthority('A', 'M')")
    public List<UserEntity> allUsers() {
        List<UserEntity> userList = userService.getAll();
        return userList;
    }
}