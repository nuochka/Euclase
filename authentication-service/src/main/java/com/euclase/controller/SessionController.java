package com.euclase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.support.SessionStatus;

import com.euclase.model.User;
@Controller
public class SessionController {
    @ModelAttribute("user")
    public User setupUser(){
        return new User();
    }


    @GetMapping("/logout")
    public String logout(@ModelAttribute("user") User user, SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return "redirect:/login?logout=true";
    }
}
