package com.eexam.restapi.service;

import java.util.List;

import com.eexam.restapi.dao.InstructorDAO;
import com.eexam.restapi.entity.Instructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    private InstructorDAO instructorDAO;

    @Override
    public List<Instructor> getAll() {
        return instructorDAO.findAll();
    }

    @Override
    public Instructor getById(int id) {
        return instructorDAO.findById(id).orElse(null);
    }

    @Override
    public Instructor getByUsername(String username) {
        return instructorDAO.findByUsername(username).orElse(null);
    }

    @Override
    public void save(Instructor instructor) {
        instructorDAO.save(instructor);
    }

    @Override
    public void delete(int id) {
        instructorDAO.deleteById(id);
    }
    
}