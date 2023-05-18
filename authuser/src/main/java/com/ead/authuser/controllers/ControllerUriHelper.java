package com.ead.authuser.controllers;

import java.net.URI;
import java.util.UUID;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ControllerUriHelper {
	
	public URI buildUriLocation(UUID userId) {
		 return ServletUriComponentsBuilder.fromCurrentRequestUri()
			        .path("/{id}")
			        .buildAndExpand(userId)
			        .toUri();
	}
	
	public UriComponents buildUriLocation() {
		 return ServletUriComponentsBuilder.fromCurrentRequestUri()
			        .path("/{id}")
			        .build();
			        //.buildAndExpand(userId)
			        // .toUri();
	}
}
