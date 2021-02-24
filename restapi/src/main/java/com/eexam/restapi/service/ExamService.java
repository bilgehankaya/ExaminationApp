package com.eexam.restapi.service;

import java.util.List;

import com.eexam.restapi.entity.Exam;

public interface ExamService {
	
	public List<Exam> getAll();
	public Exam getById(int id);
	public Exam getByUrl(String url);
	public void save(Exam exam);
	public void delete(int id); 


}
