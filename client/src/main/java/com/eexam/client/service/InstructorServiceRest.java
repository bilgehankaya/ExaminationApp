package com.eexam.client.service;

import com.eexam.client.dto.InstructorDto;
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
public class InstructorServiceRest implements InstructorService {

    @Autowired
    public RestTemplate restTemplate;

    @Value("${eexam.rest.url}")
    public String eexamRestUrl;

    @Override
    public InstructorDto getInstructor() {
               
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Bearer " + authenticatedUser.getJwt());
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<InstructorDto> response= restTemplate.exchange(eexamRestUrl + "/instructors", HttpMethod.GET, entity, InstructorDto.class);

        System.out.println("Finish!!");

        return response.getBody();
    }
    
}