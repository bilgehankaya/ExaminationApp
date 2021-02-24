package com.eexam.client.controller;

import javax.validation.Valid;

import com.eexam.client.dto.MessageResponse;
import com.eexam.client.dto.SignUp;
import com.eexam.client.model.User;
import com.eexam.client.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {


    @Autowired
    UserService userServiceRest;

    @GetMapping("/show")
    public String showRegisterPage(Model model) {

        model.addAttribute("user", new User());

        return "register-page";
    }

    @PostMapping("/process")
    public String processRegistration(@Valid @ModelAttribute("user") User user,
                                      BindingResult bindingResult,
                                      Model model) {
        
        System.out.println("RegisterPrcess");

        if(bindingResult.hasErrors()) return "register-page";

        SignUp registeredUser = SignUp.builder()
                                    .username(user.getUsername())
                                    .password(user.getPassword())
                                    .firstName(user.getFirstName())
                                    .lastName(user.getLastName())
                                    .phoneNumber(user.getPhoneNumber())
                                    .email(user.getEmail())
                                    .role(user.getRole())
                                    .build();
        ResponseEntity<MessageResponse> response = userServiceRest.register(registeredUser);
        if (response.getBody().getStatus() != HttpStatus.OK.value()) {
            model.addAttribute("registrationError", "User name already exists.");
            return "register-page";
        }

        model.addAttribute("registrationSuccess", "Registered successfully.");
        
        return "login-page";
    }

}