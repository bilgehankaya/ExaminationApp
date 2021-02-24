package com.eexam.restapi.dao;

import java.util.Optional;

import com.eexam.restapi.entity.Instructor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorDAO extends JpaRepository<Instructor, Integer> {
    Optional<Instructor> findByUsername(String username);
}