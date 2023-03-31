package com.example.ttest.service;

import com.example.ttest.dao.UserRepository;
import com.example.ttest.model.User;
//import com.example.ttest.dao.UserDao;
import com.example.ttest.config.JwtUtils;
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
        User user = userRepository.findById(userLoginRequest.getId()).orElse(null);
        String userName = user.getUsername();
        String token = jwtUtils.generateToken(userName);
        return token;
    }

    public User getByUserName(String userName){
        User user = userRepository.findByUserName(userName).orElse(null);
        return user;
    }

}
