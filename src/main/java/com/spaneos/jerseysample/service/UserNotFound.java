package com.spaneos.jerseysample.service;

public class UserNotFound extends RuntimeException {
	private static final long serialVersionUID = -1133852216554146249L;
	private final String message;
	
	public UserNotFound(String message) {
		this.message=message;
	}

	public String getMessage() {
		return message;
	}

}
