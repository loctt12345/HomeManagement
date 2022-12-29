/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loctt.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author loc12345
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.loctt.repository",
    "com.loctt.service"
})
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login")
                .usernameParameter("username").passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/").failureUrl("/login?error");
        http.logout().logoutSuccessUrl("/login");
        
        http.exceptionHandling().accessDeniedPage("/error");
        
        http.authorizeRequests().antMatchers("/").hasAnyAuthority("admin", "user")
                .antMatchers("/manage/**").hasAuthority("admin")
                .antMatchers("/pay").hasAuthority("admin")
                .antMatchers("/info").hasAuthority("admin")
                .antMatchers("/contact").hasAnyAuthority("admin", "user")
                .antMatchers("/changePasswordPage").hasAnyAuthority("admin", "user")
                .antMatchers("/changePassword").hasAnyAuthority("admin", "user")
                .antMatchers("/updatePage").hasAuthority("admin")
                .antMatchers("/updateMember").hasAuthority("admin")
                .antMatchers("/deleteMember").hasAuthority("admin");
//        http.authorizeRequests().antMatchers("/**").access("hasRole('Chủ nhà')")
//                .antMatchers("/login").permitAll();
        http.csrf().disable();
    }

}
