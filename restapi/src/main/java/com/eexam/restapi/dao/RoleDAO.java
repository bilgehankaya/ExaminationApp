package com.eexam.restapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import com.eexam.restapi.entity.Role;
import com.eexam.restapi.model.EnumRole;

public interface RoleDAO extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(EnumRole name); 

}