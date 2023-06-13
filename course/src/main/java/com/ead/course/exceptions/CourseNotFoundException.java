package com.ead.course.exceptions;

public class CourseNotFoundException extends RuntimeException {
	
	public CourseNotFoundException(String msg) {
		super(msg);
	}
}
