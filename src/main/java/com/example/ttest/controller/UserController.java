package com.example.ttest.controller;

import com.example.ttest.model.UserLoginRequest;
import com.example.ttest.service.UserService;
import com.example.ttest.config.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")

    public String login(@RequestBody UserLoginRequest userLoginRequest) {
        String token = userService.login(userLoginRequest);

        return token;
    }

    @GetMapping("/auth")
    public String getAuth(@RequestHeader("Authorization") String tokenHeader) {

//        String token = tokenHeader.substring(7);// 刪除 "Bearer " 前綴
        String token = tokenHeader;
        if (jwtUtils.validateToken(token)) {
            String userName = jwtUtils.getUserNameFromToken(token);
            return "Hello, " + userName;
        } else {
            throw new RuntimeException("Invalid token");
        }
    }

}