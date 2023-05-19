package com.ead.authuser.controllers;

import static org.springframework.hateoas.IanaLinkRelations.COLLECTION;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequestUri;

import java.util.Map;
import java.util.UUID;

import org.springframework.hateoas.Link;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ControllerHelper {

	public static String buildUriLocation(UUID userId) {
		return fromCurrentRequestUri()
				.path("/{id}")
				.buildAndExpand(userId)
				.toUri()
				.toString();
	}

	public String buildUriLocationFromCurrentRequest() {
		return fromCurrentRequestUri().toUriString();
	}

	public Link buildLinkWithSelfRelation(Class<?> controller, UUID userId) {
		return linkTo(controller).slash(userId).withSelfRel();
	}

	public Link buildLinkWithRelation(Class<?> controller) {
		return linkTo(controller).withRel(COLLECTION);
	}

	public Map<String, Link> buildLinkWithSelfAndRelation(Class<?> controller, UUID userId) {
		return Map.of("linkWithSelfRelation", buildLinkWithSelfRelation(controller, userId),
				"linkWithRelation",buildLinkWithRelation(controller));
	}

}
