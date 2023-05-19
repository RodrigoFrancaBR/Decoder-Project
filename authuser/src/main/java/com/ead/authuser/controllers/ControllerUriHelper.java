package com.ead.authuser.controllers;

import java.util.UUID;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ControllerUriHelper {

	public String buildUriLocation(UUID userId) {
		return ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(userId).toUri()
				.toString();
	}

	public String buildUriLocationFromCurrentRequest() {
		return ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();
	}

	public Link buildLinkWithSelfRelation(Class<?> controller, UUID userId) {
		return WebMvcLinkBuilder.linkTo(controller).slash(userId).withSelfRel();
	}

	public Link buildLinkWithRelation(Class<?> controller) {
		return WebMvcLinkBuilder.linkTo(controller).withRel(IanaLinkRelations.COLLECTION);
	}

}
