package com.eexam.restapi.exception;

public class ExamNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -7693500771080498959L;

	public ExamNotFoundException() {
		super();
	}

	public ExamNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ExamNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExamNotFoundException(String message) {
		super(message);
	}

	public ExamNotFoundException(Throwable cause) {
		super(cause);
	}
	
}
