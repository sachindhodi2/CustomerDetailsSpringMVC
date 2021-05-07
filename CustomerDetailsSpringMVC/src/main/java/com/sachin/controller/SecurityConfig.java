//package com.sachin.controller;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter  {
//
//	@Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//         http
//         .authorizeRequests()
//         .antMatchers("/CustomerDetailsSpringMVC/customerdetails/*").hasRole("USER")
//         .anyRequest().authenticated()
//         .and()
//         .formLogin()
//         .loginPage("/login")
//         .permitAll();
//    }
//	
//	
//	@Bean  
//    public UserDetailsService userDetailsService() {  
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();  
//        manager.createUser(User.withDefaultPasswordEncoder().username("sachin").  
//        password("dhodi").roles("USER").build());  
//        return manager;  
//    }  
//	
//}
