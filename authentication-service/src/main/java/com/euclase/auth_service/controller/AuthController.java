package com.euclase.auth_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.euclase.auth_service.model.User;
import com.euclase.auth_service.repository.UserRepository;
import com.euclase.auth_service.service.UserService;

import jakarta.validation.Valid;

@Controller
public class AuthController {

    private UserRepository userRepository;
    private UserService userService;


    @Autowired
    public AuthController(UserRepository userRepository, UserService userService){
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/")
    public String welcomePage(){
        return "welcome";
    }

    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }

    @GetMapping("/home")
    public String homePage(){
        return "home";
    }

    @GetMapping("registration")
    public String showRegistrationForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registration";
        }

        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            model.addAttribute("error", "This account with this email already exists!");
            return "registration";
        }

        userService.saveUser(user.getUsername(), user.getEmail(), user.getPassword());
        model.addAttribute("sucess", "User registered successfully!");
        return "redirect:/login";
    }
}
