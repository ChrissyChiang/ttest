package com.example.ttest.controller;

import com.example.ttest.config.JwtUtils;
import com.example.ttest.model.UserLoginRequest;
import com.example.ttest.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    public static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/generate")

    public String generateToken(@RequestBody UserLoginRequest userLoginRequest) {
        String token = userService.login(userLoginRequest);

        return token;
    }//generateToken

    @GetMapping("/validate")
    public String validateToken(@RequestHeader("Authorization") String tokenHeader) {
//        String token = tokenHeader.substring(7);// 刪除 "Bearer " 前綴
        String token = tokenHeader;
        String userName = null;
        try {
            if (jwtUtils.validateToken(token)) {
                userName = jwtUtils.getUserNameFromToken(token);
            }
        } catch (RuntimeException e) {
            LOGGER.error("Invalid token");
        }
        return "Hello, " + userName;
    }//validateToken
}