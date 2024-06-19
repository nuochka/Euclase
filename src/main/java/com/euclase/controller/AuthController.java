package com.euclase.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.euclase.repository.UserRepository;
import com.euclase.service.UserService;

@RestController
public class AuthController {

    private UserRepository userRepository;

    private UserService userService;

    public AuthController(UserService userService){
        this.userService = userService;
    }


    @GetMapping("/")
    public String welcomePage(){
        return "welcome";
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";   
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    
}
