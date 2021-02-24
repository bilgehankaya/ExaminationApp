package com.eexam.restapi.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.eexam.restapi.entity.Exam;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ExamDAOImpl implements ExamDAO {
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Exam> getAllEntities() {
		
		Session currentSession = entityManager.unwrap(Session.class);

		List<Exam> exams = currentSession.createQuery("from Exam", Exam.class)
										 .getResultList();

		return exams;
	}

	@Override
	public Exam getEntityById(int id) {

		Session currentSession = entityManager.unwrap(Session.class);
		
		Exam exam = currentSession.createQuery("SELECT DISTINCT e FROM Exam e WHERE e.id=:id", Exam.class)
								  .setParameter("id", id)
								  .getSingleResult();
						   
		return exam;

	}

	@Override
	public Exam getEntityByURL(String url) {

		Session currentSession = entityManager.unwrap(Session.class);
		
		Exam exam = currentSession.createQuery("SELECT DISTINCT e FROM Exam e WHERE e.uniqueURL=:url", Exam.class)
								  .setParameter("url", url)
						   		  .getSingleResult();
						   
		return exam;
	}

	@Override
	public void saveEntity(Exam exam) {
		
		Session currentSession = entityManager.unwrap(Session.class);

		currentSession.merge(exam);
	}

	@Override
	public void deleteEntity(int id) {
		
		Session currentSession = entityManager.unwrap(Session.class);

		Exam exam = currentSession.get(Exam.class, id);
		currentSession.remove(exam);
	}
}
