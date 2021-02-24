package com.eexam.restapi.service;

import com.eexam.restapi.payload.MessageResponse;
import com.eexam.restapi.payload.SignupRequest;

public interface RegistrationService {
    public MessageResponse register(SignupRequest signupRequest);
}