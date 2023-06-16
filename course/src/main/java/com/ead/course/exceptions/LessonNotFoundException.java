package com.ead.course.exceptions;

public class LessonNotFoundException extends RuntimeException {
	public LessonNotFoundException(String msg) {
		super(msg);
	}
}
