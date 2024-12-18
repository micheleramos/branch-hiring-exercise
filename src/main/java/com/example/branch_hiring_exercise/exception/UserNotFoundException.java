package com.example.branch_hiring_exercise.exception;


public class UserNotFoundException extends RuntimeException {
	
	// This exception will be thrown when the github user or repos does not exist (null) or is not visible
	// Future enhancement: separate exception for repos not found.
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
