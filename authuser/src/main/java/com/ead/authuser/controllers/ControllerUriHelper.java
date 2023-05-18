package com.ead.authuser.controllers;

import java.util.UUID;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ControllerUriHelper {

	public String buildUriLocation(UUID userId) {
		return ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path("/{id}")
				.buildAndExpand(userId).toUri()
				.toString();
	}

	public String buildUriLocationFromCurrentRequest() {
		return ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();
	}
}
