package com.example.ttest.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder getPasswordEncode() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/api/auth/logins").authenticated()
                .antMatchers(HttpMethod.POST).permitAll()
                .antMatchers(HttpMethod.GET).authenticated()
//                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin().loginProcessingUrl("/api/auth/login")
//                .formLogin()
//                .loginProcessingUrl("/api/auth/auth")
                .permitAll()
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                        Authentication authentication) throws IOException, ServletException {
                        response.setStatus(HttpServletResponse.SC_OK);
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                                        AuthenticationException exception) throws IOException, ServletException {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    }
                });
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationService)
                .userDetailsService(userDetailsService)//使用 UserDetailsService 這個介面來注入，Spring 會自動找到有實作這個介面的類別，也就是 SpringUserService。
                .passwordEncoder(new BCryptPasswordEncoder());

    }


}
