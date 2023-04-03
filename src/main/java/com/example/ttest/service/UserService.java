package com.example.ttest.service;

import com.example.ttest.config.JwtUtils;
import com.example.ttest.entity.PermissionEntity;
import com.example.ttest.entity.UserEntity;
import com.example.ttest.model.UserLoginRequest;
import com.example.ttest.repository.PermissionRepository;
import com.example.ttest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private JwtUtils jwtUtils;

    public String generate(UserLoginRequest userLoginRequest) {
        UserEntity user = userRepository.findById(userLoginRequest.getId()).orElse(null);
        String userName = user.getUserName();
        String token = jwtUtils.generateToken(userName);
        return token;
    }

    public UserEntity getByUserName(String userName) {
        UserEntity user = userRepository.findByUserName(userName).orElse(null);
        return user;
    }

    public List<UserEntity> getAll() {
        List<UserEntity> userList = userRepository.findAll();
        return userList;
    }

    public String getAuth(String userAuth) {
        PermissionEntity perm = permissionRepository.findByAuth(userAuth);
        String auth = perm.getAuthority();
        return auth;
    }


    public Integer createUser(UserLoginRequest userLoginRequest) {
            UserEntity userEntity = new UserEntity();
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            System.out.println(userLoginRequest.getName());
            userEntity.setUserName(userLoginRequest.getName());
            userEntity.setUserPassword(bCryptPasswordEncoder.encode(userLoginRequest.getUserPwd()));
            userEntity.setUserAuth(userLoginRequest.getUserAuth());
            UserEntity user = userRepository.save(userEntity);
            return user.getId();
        }

    public UserEntity getById(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }

}
