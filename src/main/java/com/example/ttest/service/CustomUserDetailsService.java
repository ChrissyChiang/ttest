package com.example.ttest.service;

import com.example.ttest.entity.UserEntity;
import com.example.ttest.enums.AuthorityEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;


    private final static Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class);


    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("=======執行自定義登入邏輯====");
        UserEntity user = userService.getByUserName(username);

        if (user == null) {
            LOGGER.error(username + "(該帳號不存在)");
            throw new UsernameNotFoundException("該帳號不存在");
        }

//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        String pwd = bCryptPasswordEncoder.encode(request.getParameter("password"));
//        String userPwd = user.getUserName();
//        if (!bCryptPasswordEncoder.matches(pwd, userPwd)) {
//            LOGGER.error("密碼不正確");
//            throw new BadCredentialsException("密碼不正確");
//        }

        String userRole = userService.getAuth(user.getUserAuth());
        LOGGER.info(user.getUserName() + "使用者權限：" + userRole);

        List<GrantedAuthority> authorities = null;
        try {
            authorities = new ArrayList<>();
            switch (AuthorityEnum.getEnum(userRole)) {
                case ADMIN:
                    authorities.add(new SimpleGrantedAuthority("ADMIN"));
                    break;
                case EMPLOYEE:
                    authorities.add(new SimpleGrantedAuthority("EMPLOYEE"));
                    break;
                case MANAGER:
                    authorities.add(new SimpleGrantedAuthority("MANAGER"));
                    break;
               }
        } catch (NullPointerException e){
            LOGGER.error("Unexpected value: " + userService.getAuth(user.getUserAuth()));
        }
        return new CustomUserDetails(user, authorities);
    }
}
