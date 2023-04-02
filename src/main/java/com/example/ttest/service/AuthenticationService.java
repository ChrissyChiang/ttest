//package com.example.ttest.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthenticationService implements AuthenticationProvider {
//
//@Autowired
//private CustomUserDetailsService customUserDetailsService;
//
//@Autowired
//private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//        System.out.println(username);
//        String password = authentication.getCredentials().toString();
//        System.out.println(password);
//
//        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
//
//        if (!bCryptPasswordEncoder.matches(password, userDetails.getPassword())) {
//            throw new BadCredentialsException("Invalid username or password");
//        }
//
//        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}
//
//
//
