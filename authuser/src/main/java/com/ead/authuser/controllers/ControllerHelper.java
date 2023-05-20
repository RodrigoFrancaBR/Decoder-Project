package com.ead.authuser.controllers;

import static org.springframework.hateoas.IanaLinkRelations.COLLECTION;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequestUri;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ControllerHelper {

	public static String buildUriLocation(UUID userId) {
		return fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(userId).toUri().toString();
	}

	public String buildUriLocationFromCurrentRequest() {
		return fromCurrentRequestUri().toUriString();
	}

	public static List<Link> getLinkWithSelfAndRelation(UUID userId, Pageable pageable) {
		return List.of(getLinkwithSelfRelation(userId), getLinkWithRelation(pageable));
	}

	public Link getLinkwithSelfRelation(UUID userId) {
		return linkTo(methodOn(UserController.class).getOneUser(userId)).withSelfRel();
	}

	public Link getLinkWithRelation(Pageable pageable) {
		return linkTo(methodOn(UserController.class).findAll(pageable))
				.withRel(COLLECTION);
	}
}
