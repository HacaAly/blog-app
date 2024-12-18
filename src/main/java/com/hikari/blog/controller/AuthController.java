package com.hikari.blog.controller;

import com.hikari.blog.request.auth.LoginRequest;
import com.hikari.blog.response.auth.LoginResponse;
import com.hikari.blog.service.JwtService;
import com.hikari.blog.service.MyUserDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @PostMapping("/api/public/login")
    public LoginResponse login (@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        if (authenticate.isAuthenticated()) {
            String token
                    = jwtService.generateToken
                    (myUserDetailsService.loadUserByUsername(loginRequest.getUsername()));

            return LoginResponse.builder().token(token).build();
        }

        throw new UsernameNotFoundException("invalid user");
    }

}
