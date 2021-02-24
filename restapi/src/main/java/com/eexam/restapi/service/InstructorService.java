package com.eexam.restapi.service;

import java.util.List;

import com.eexam.restapi.entity.Instructor;

public interface InstructorService {

    public List<Instructor> getAll();
	public Instructor getById(int id);
	public Instructor getByUsername(String username);
	public void save(Instructor instructor);
	public void delete(int id); 
    
}