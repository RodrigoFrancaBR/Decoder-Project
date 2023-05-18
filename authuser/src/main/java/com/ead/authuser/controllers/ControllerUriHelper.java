package com.ead.authuser.controllers;

import java.net.URI;
import java.util.UUID;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ControllerUriHelper {
	
	public URI buildUriLocation(UUID userId) {
		 return ServletUriComponentsBuilder.fromCurrentRequestUri()
			        .path("/{id}")
			        .buildAndExpand(userId)
			        .toUri();
	}
}
