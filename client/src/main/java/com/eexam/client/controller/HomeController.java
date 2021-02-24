package com.eexam.client.controller;

import com.eexam.client.dto.User;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String hello() {

        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String role = authenticatedUser.getRoles().get(0).trim();
        System.out.println(role);
        if(role.equals("ROLE_PARTICIPANT")) {
            return "redirect:part/home";
        }
        else {
            return "redirect:inst/home";
        }
    }
}