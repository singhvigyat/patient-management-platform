package com.example.authservice.config;

import io.jsonwebtoken.security.Password;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        // as we're handling security throught the api gateway, we're going to permit all the
        // requests in this flow, because we're not exposing auth-service port direclty to the
        // internet, so we can permit all the request coming to this.
        http.authorizeHttpRequests(authorize -> authorize
                .anyRequest()
                .permitAll())
                .csrf(AbstractHttpConfigurer::disable) ;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
