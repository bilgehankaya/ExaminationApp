package com.eexam.client.service;

import com.eexam.client.dto.ExamDto;
import com.eexam.client.dto.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class ExamServiceRest implements ExamService {

    @Autowired
    public RestTemplate restTemplate;

    @Value("${eexam.rest.url}")
    public String eexamRestUrl;

    @Override
    public ExamDto getExamById(int id) {
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Bearer " + authenticatedUser.getJwt());
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<ExamDto> response= restTemplate.exchange(eexamRestUrl + "/exams/"+ id, HttpMethod.GET, entity, ExamDto.class);

        return (response.getStatusCode().value() == HttpStatus.OK.value()) ? response.getBody() : null;
    }

    @Override
    public ExamDto getExamByUrl(String url) {
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Bearer " + authenticatedUser.getJwt());
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<ExamDto> response= restTemplate.exchange(eexamRestUrl + "/exams/byUrl/" + url, HttpMethod.GET, entity, ExamDto.class);

        return (response.getStatusCode().value() == HttpStatus.OK.value()) ? response.getBody() : null;
    }
    
}