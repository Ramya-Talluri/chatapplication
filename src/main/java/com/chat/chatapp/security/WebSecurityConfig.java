package com.chat.chatapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests()  // For Spring Security 6.x
            .requestMatchers("/login", "/logout").permitAll()  // Allows unauthenticated access to these URLs
            .anyRequest().authenticated()  // Requires authentication for other requests
        .and()
            .formLogin().permitAll()  // Allow access to the login form for all users
        .and()
            .logout().permitAll();  // Allow logout for all users
        return http.build();
    }

    // In-memory authentication configuration
    @Bean
    public UserDetailsService userDetailsService() {
        // Creating two users: one with 'USER' role and another with 'ADMIN' role
        var user = User.withDefaultPasswordEncoder()
            .username("user")
            .password("password")
            .roles("USER")
            .build();
        var admin = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("admin123")
            .roles("ADMIN")
            .build();
        
        return new InMemoryUserDetailsManager(user, admin);  // In-memory store for users
    }
}
