package com.ead.authuser.exceptions;

public class UserConflictException extends RuntimeException {
	
	public UserConflictException(String msg) {
		super(msg);
	}
}
