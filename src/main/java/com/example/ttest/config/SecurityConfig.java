package com.example.ttest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
        protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/auth/login").authenticated()
//                .antMatchers(HttpMethod.POST).permitAll()
                .antMatchers(HttpMethod.GET).permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin();
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and().authorizeRequests()
//                .anyRequest()
//                .authenticated()
//                .and().formLogin().disable();//禁用預設login頁面
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)//使用 UserDetailsService 這個介面來注入，Spring 會自動找到有實作這個介面的類別，也就是 SpringUserService。
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
