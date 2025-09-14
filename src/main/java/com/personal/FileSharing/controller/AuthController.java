package com.personal.FileSharing.controller;

import com.personal.FileSharing.entity.Users;
import com.personal.FileSharing.service.CustomUserDetailsService;
import com.personal.FileSharing.service.UsersService;
import com.personal.FileSharing.utility.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UsersService usersService;
    private final JwtUtility jwtUtil;
    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationConfiguration authenticationConfiguration;
    @Autowired
    public AuthController(UsersService usersService, AuthenticationConfiguration authenticationConfiguration, JwtUtility jwtUtil, CustomUserDetailsService userDetailsService, PasswordEncoder passwordEncoder, AuthenticationConfiguration authenticationConfiguration1) {
        this.usersService = usersService;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationConfiguration = authenticationConfiguration1;
    }
    @PostMapping("/signup")
    public ResponseEntity<?>signup(@RequestBody Users user){
        usersService.saveUsers(user);
        return ResponseEntity.ok("User registered");
    }

    @PostMapping("/login")
    public ResponseEntity<?>login(@RequestBody Users user){

        try {
            AuthenticationManager authenticationManager=authenticationConfiguration.getAuthenticationManager();
           Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            String jwt = jwtUtil.generateToken(userDetails);
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credential");
        }
    }



}
