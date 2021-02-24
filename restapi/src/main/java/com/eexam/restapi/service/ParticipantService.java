package com.eexam.restapi.service;

import java.util.List;

import com.eexam.restapi.entity.Participant;
import com.eexam.restapi.payload.ResultRequest;

public interface ParticipantService {

    public List<Participant> getAll();
    public Participant getById(int id);
    public Participant getByUsername(String username);
    public void save(Participant participant);
    public void delete(int id);
    public void saveResult(ResultRequest resultRequest);

}