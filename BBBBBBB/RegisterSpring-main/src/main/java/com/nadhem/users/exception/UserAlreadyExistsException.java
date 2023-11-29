package com.nadhem.users.exception;

public class UserAlreadyExistsException extends RuntimeException {

	public UserAlreadyExistsException(String message) {
		super(message);
	}
}
