package com.eexam.client.service;

import com.eexam.client.dto.JwtResponse;
import com.eexam.client.dto.MessageResponse;
import com.eexam.client.dto.SignUp;
import com.eexam.client.dto.SigninRequest;

import org.springframework.http.ResponseEntity;

public interface UserService {
    public ResponseEntity<JwtResponse> signin(SigninRequest request);
    public ResponseEntity<MessageResponse> register(SignUp user);
}