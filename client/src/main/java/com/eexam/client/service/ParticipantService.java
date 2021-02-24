package com.eexam.client.service;

import com.eexam.client.dto.ParticipantDto;

public interface ParticipantService {

    public ParticipantDto getParticipant();
    public void enrollUserToExam(String url);
    public void saveResult();
    
}