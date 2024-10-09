package com.learn.quickbooksApi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/", "/connectToQuickbooks", "/oauth2redirect", "/connected", "/getCompanyInfo").permitAll()
                        .anyRequest().authenticated()  // Protect other pages
                );
        return http.build();
    }
}