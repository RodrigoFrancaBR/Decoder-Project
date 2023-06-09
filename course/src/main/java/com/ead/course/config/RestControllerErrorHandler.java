package com.ead.course.config;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ead.course.exceptions.ModuleNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestControllerAdvice
public class RestControllerErrorHandler {

	private final MessageSource messageSource;

	@ExceptionHandler(value = { ModuleNotFoundException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String moduleNotFoundException(ModuleNotFoundException ex) {
		return ex.getMessage();
	}
}
