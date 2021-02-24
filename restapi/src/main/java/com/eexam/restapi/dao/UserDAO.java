package com.eexam.restapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import com.eexam.restapi.entity.User;

public interface UserDAO extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    
}