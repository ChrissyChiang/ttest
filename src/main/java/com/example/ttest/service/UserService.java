package com.example.ttest.service;

import com.example.ttest.config.JwtUtils;
import com.example.ttest.dao.UserRepository;
import com.example.ttest.model.UserEntity;
import com.example.ttest.model.UserLoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtils jwtUtils;

    public String login(UserLoginRequest userLoginRequest) {
        UserEntity user = userRepository.findById(userLoginRequest.getId()).orElse(null);
        String userName = user.getUserName();
        String token = jwtUtils.generateToken(userName);
        return token;
    }

    public UserEntity getByUserName(String userName){
        UserEntity user = userRepository.findByUserName(userName).orElse(null);
        return user;
    }

}
