package com.spaneos.jerseysample.exception;

public class InvalidUserException extends RuntimeException {

	private static final long serialVersionUID = 4214834604918947379L;
	
	private final String message;
	
	public InvalidUserException() {
		this.message="Invalid user";
	}

	public InvalidUserException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
