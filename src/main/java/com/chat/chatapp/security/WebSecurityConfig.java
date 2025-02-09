package com.chat.chatapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  // Disable CSRF for simplicity (disable for APIs, but do it carefully)
            .authorizeRequests()
                .requestMatchers("/auth/signup", "/auth/login", "/auth/logout", "/messages/send", "/chats/start", "/messages/read/{messageId}", "/messages/unread/{userId}").permitAll()  // Allow signup and login without authentication
                .anyRequest().authenticated()  // Require authentication for all other requests
            .and()
            .formLogin().disable();  // Disable the default login page (for custom auth handling)

        return http.build();  // Spring Security 6.x uses this to build the HttpSecurity object
    }
}
