package com.euclase.auth_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.euclase.auth_service.model.User;
import com.euclase.auth_service.model.Task;
import com.euclase.auth_service.repository.UserRepository;
import com.euclase.auth_service.service.UserService;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class AuthController {

    private UserRepository userRepository;
    private UserService userService;
    private RestTemplate restTemplate;


    @Autowired
    public AuthController(UserRepository userRepository, UserService userService, RestTemplate restTemplate){
        this.userRepository = userRepository;
        this.userService = userService;
        this.restTemplate = restTemplate;
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
    public String homePage(Model model){
        String tasksApiUrl = "http://localhost:8081/api/tasks";
        Task[] tasksArray = restTemplate.getForObject(tasksApiUrl, Task[].class);
        List<Task> tasks = new ArrayList<>(Arrays.asList(tasksArray));
        model.addAttribute("tasks", tasks);
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
