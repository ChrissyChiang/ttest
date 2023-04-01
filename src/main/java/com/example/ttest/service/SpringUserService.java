package com.example.ttest.service;

import com.example.ttest.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SpringUserService implements UserDetailsService {

    @Autowired
    private UserService userService;


    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("=======執行自定義登入邏輯====");
        UserEntity user = userService.getByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("該帳號不存在");
        }

        //寫死的
//        if (!username.equals("user")) {
//            throw new UsernameNotFoundException("使用者不存在");
//        }
        System.out.println("======="+username+"====");

        return new CustomUserDetails(user ,
                //自定義許可權
                AuthorityUtils.commaSeparatedStringToAuthorityList("A"));

//        List<GrantedAuthority> authorities = buildUserAuthority(user.getUserAuth());
//
//        return new CustomUserDetails(user, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(String userRole) {
        Set<GrantedAuthority> setAuths = new HashSet<>();
        setAuths.add(new SimpleGrantedAuthority(userRole));
        List<GrantedAuthority> Result = new ArrayList<>(setAuths);
        return Result;
    }
}
