package com.eexam.restapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eexam.restapi.dao.ExamDAOImpl;
import com.eexam.restapi.entity.Exam;

@Service
public class ExamServiceImpl implements ExamService {

	@Autowired
	public ExamDAOImpl examDAO;
	
	@Override
	@Transactional
	public List<Exam> getAll() {
		return examDAO.getAllEntities();
	}

	@Override
	@Transactional
	public Exam getById(int id) {
		return examDAO.getEntityById(id);
	}

	@Override
	@Transactional
	public Exam getByUrl(String url) {
		return examDAO.getEntityByURL(url);
	}

	@Override
	@Transactional
	public void save(Exam exam) {
		examDAO.saveEntity(exam);
	}

	@Override
	@Transactional
	public void delete(int id) {
		examDAO.deleteEntity(id);
	}

}
