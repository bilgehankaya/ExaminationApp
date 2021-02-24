package com.eexam.restapi.service;

import java.util.List;

import com.eexam.restapi.dao.ParticipantDAO;
import com.eexam.restapi.entity.Participant;
import com.eexam.restapi.entity.Result;
import com.eexam.restapi.payload.ResultRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    @Autowired
    private ParticipantDAO participantDAO;

    @Override
    public List<Participant> getAll() {
        return participantDAO.findAll();
    }

    @Override
    public Participant getById(int id) {
        return participantDAO.findById(id).orElse(null);
    }

    @Override
    public Participant getByUsername(String username) {
        return participantDAO.findByUsername(username).orElse(null);
    }

    @Override
    public void save(Participant participant) {
        participantDAO.save(participant);
    }

    @Override
    public void delete(int id) {
        participantDAO.deleteById(id);
    }

    @Override
    public void saveResult(ResultRequest resultRequest) {
        Participant participant = participantDAO.findById(resultRequest.getParticipantId()).orElse(null);

        Result result = participant.getResults().stream()
                                   .filter(res -> res.getId() == resultRequest.getId())
                                   .findFirst()
                                   .get();
        result.setJoined(true);
        result.setCorrectNumber(resultRequest.getCorrectNumber());
        result.setIncorrectNumber(resultRequest.getIncorrectNumber());
        result.setEmptyNumber(resultRequest.getEmptyNumber());
        
        participantDAO.save(participant);                      
    }
    
    
}