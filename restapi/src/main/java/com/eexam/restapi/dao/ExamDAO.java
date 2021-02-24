package com.eexam.restapi.dao;

import java.util.List;

import com.eexam.restapi.entity.Exam;

public interface ExamDAO {
	
    public List<Exam> getAllEntities();
	public Exam getEntityById(int id);
	public Exam getEntityByURL(String url);
	public void saveEntity(Exam entity);
	public void deleteEntity(int id);

}
