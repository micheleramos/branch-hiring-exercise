package com.example.branch_hiring_exercise.exception;

public class ExternalServiceException extends RuntimeException {
	
	// This exception will be thrown when there is an upstream error from the Github API.
	
    public ExternalServiceException(String message) {
        super(message);
    }

    public ExternalServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
