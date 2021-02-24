package com.eexam.restapi.controller;

import java.util.List;
import java.util.UUID;

import com.eexam.restapi.converter.ExamConverter;
import com.eexam.restapi.payload.ExamDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eexam.restapi.entity.Exam;
import com.eexam.restapi.entity.Instructor;
import com.eexam.restapi.exception.ExamErrorResponse;
import com.eexam.restapi.exception.ExamNotFoundException;
import com.eexam.restapi.service.ExamService;
import com.eexam.restapi.service.InstructorService;

@RestController
@RequestMapping("/api")
public class ExamRestController {

	@Autowired
	ExamService examService;

	@Autowired
	InstructorService instructorService;

	@GetMapping("/exams")
	public List<ExamDto> getExams() {
		return ExamConverter.getExamDtos(examService.getAll());
	}

	@GetMapping("/exams/{examId}")
	public ExamDto getExam(@PathVariable int examId) {
		
		Exam exam = examService.getById(examId);

		if(exam == null) { throw new ExamNotFoundException("Exam not found by " + examId + " examId"); }

		// Convert exam to its corresponding examDto
		ExamDto examDto = ExamConverter.getExamDto(exam);

		return examDto;
	}
	
	@GetMapping("/exams/byUrl/{url}")
	public ExamDto getExamByUniqueURL(@PathVariable String url) {
		
		Exam exam = examService.getByUrl(url);
		if(exam == null) { throw new ExamNotFoundException("Exam not found by " + url + " url"); }

		// Convert exam to its corresponding examDto
		ExamDto examDto = ExamConverter.getExamDto(exam);
		
		return examDto;
	}

	@PreAuthorize("hasRole('INSTRUCTOR')")
	@PostMapping("/exams")
	public ExamDto addExam(@RequestBody Exam exam) {
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Instructor instructor = instructorService.getByUsername(username);

        if(instructor == null) new RuntimeException("PostMapping: Username is not found.");
		
		exam.setId(0);
		exam.setUniqueURL(UUID.randomUUID().toString());

		exam.setCreator(instructor);
		instructor.getCreatedExams().add(exam);
		examService.save(exam);

		return ExamConverter.getExamDto(exam);
	}
	
	@PreAuthorize("hasRole('INSTRUCTOR')")
	@PutMapping("/exams/{examId}")
	public Exam updateExam(@RequestBody Exam exam,
						   @PathVariable int examId) {
		
		Exam findedExam = examService.getById(examId);
		
		if(findedExam == null) { throw new ExamNotFoundException("Exam not found by " + examId + " examId"); }

		exam.setId(examId);
		exam.setUniqueURL(findedExam.getUniqueURL());
		examService.save(exam);
		
		return exam;
	}
	
	@PreAuthorize("hasRole('INSTRUCTOR')")
	@DeleteMapping("/exams/{examId}")
	public ExamErrorResponse deleteExam(@PathVariable int examId) {
		
		Exam exam = examService.getById(examId);
		
		if(exam == null) { throw new ExamNotFoundException("Exam not found by " + examId + " examId"); }
		
		examService.delete(examId);
		
		ExamErrorResponse message = ExamErrorResponse.builder()
								   .status(HttpStatus.OK.value())
								   .message("Deleted exam id - " + examId)
								   .timeStamp(System.currentTimeMillis())
								   .build();
		
		return message;
	}

}
