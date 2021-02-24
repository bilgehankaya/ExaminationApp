package com.eexam.restapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExamRestExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ExamErrorResponse> handleException(ExamNotFoundException exception) {
		
		ExamErrorResponse error = ExamErrorResponse.builder()
									 .status(HttpStatus.NOT_FOUND.value())
									 .message(exception.getMessage())
									 .timeStamp(System.currentTimeMillis())
									 .build();
									 
		return new ResponseEntity<ExamErrorResponse>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ExamErrorResponse> handleException(Exception exception) {
		
		ExamErrorResponse error = ExamErrorResponse.builder()
									 .status(HttpStatus.BAD_REQUEST.value())
									 .message(exception.getMessage())
									 .timeStamp(System.currentTimeMillis())
									 .build();
									 
		return new ResponseEntity<ExamErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
}
