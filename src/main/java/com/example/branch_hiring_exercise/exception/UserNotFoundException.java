package com.example.branch_hiring_exercise.exception;


public class UserNotFoundException extends RuntimeException {
	
	// This exception will be thrown when the github user does not exist or is not visible
	
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
