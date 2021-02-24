package com.eexam.restapi.dao;

import java.util.Optional;

import com.eexam.restapi.entity.Participant;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantDAO extends JpaRepository<Participant, Integer> {
    Optional<Participant> findByUsername(String username);
}