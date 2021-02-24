package com.eexam.restapi.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.eexam.restapi.payload.*;
import com.eexam.restapi.service.*;
import com.eexam.restapi.util.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
	AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RegistrationService registrationService;

    @PostMapping("/auth/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody SigninRequest signinRequest) {

        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(signinRequest.getUsername(), signinRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(authReq);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                             .map(role -> role.getAuthority())
                             .collect(Collectors.toList());

        JwtResponse response = JwtResponse.builder()
                               .jwt(jwt)
                               .id(userDetails.getId())
                               .username(userDetails.getUsername())
                               .email(userDetails.getEmail())
                               .roles(roles)
                               .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/auth/signup")
    public MessageResponse registerUser(@RequestBody SignupRequest signupRequest) {

        MessageResponse response = registrationService.register(signupRequest);

        return response;
    }

}