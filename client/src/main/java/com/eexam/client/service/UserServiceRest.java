package com.eexam.client.service;

import com.eexam.client.dto.JwtResponse;
import com.eexam.client.dto.MessageResponse;
import com.eexam.client.dto.SignUp;
import com.eexam.client.dto.SigninRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceRest implements UserService {

    @Autowired
    public RestTemplate restTemplate;

    @Value("${eexam.rest.url}")
    public String eexamRestUrl;

    @Override
    public ResponseEntity<JwtResponse> signin(SigninRequest request) {
        ResponseEntity<JwtResponse> response = restTemplate.postForEntity(eexamRestUrl + "/auth/signin", request, JwtResponse.class);
        return response;
    }   

    @Override
    public ResponseEntity<MessageResponse> register(SignUp user) {
        ResponseEntity<MessageResponse> response = restTemplate.postForEntity(eexamRestUrl + "/auth/signup", user, MessageResponse.class);
        return response;
    }
    
}