package com.eexam.client.service;

import com.eexam.client.dto.ParticipantDto;
import com.eexam.client.dto.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class ParticipantServiceRest implements ParticipantService {

    @Autowired
    public RestTemplate restTemplate;

    @Value("${eexam.rest.url}")
    public String eexamRestUrl;

    @Override
    public ParticipantDto getParticipant() {
        
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Bearer " + authenticatedUser.getJwt());
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<ParticipantDto> response= restTemplate.exchange(eexamRestUrl + "/participants", HttpMethod.GET, entity, ParticipantDto.class);

        return response.getBody();
    }

    @Override
    public void enrollUserToExam(String url) {
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Bearer " + authenticatedUser.getJwt());
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<String> response= restTemplate.exchange(eexamRestUrl + "/participants/enroll?url=" + url, HttpMethod.GET, entity, String.class);
    }

    @Override
    public void saveResult() {

    }
    
}