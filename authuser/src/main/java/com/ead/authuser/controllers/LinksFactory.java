package com.ead.authuser.controllers;

import static org.springframework.hateoas.IanaLinkRelations.COLLECTION;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequestUri;

import java.util.UUID;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.stereotype.Component;

@Component
public class LinksFactory {

	private static final TemplateVariables TEMPLATE_VARIABLES = new TemplateVariables(
			new TemplateVariable("page", VariableType.REQUEST_PARAM),
			new TemplateVariable("size", VariableType.REQUEST_PARAM),
			new TemplateVariable("sort", VariableType.REQUEST_PARAM),
			new TemplateVariable("direction", VariableType.REQUEST_PARAM));

	public Link getLinkWithRelation() {		
		var templateVariables = TEMPLATE_VARIABLES
				.concat(buildFilterTemplateVariables());
		var uriTemplate = UriTemplate.of(getUrl(), templateVariables);
		
		return Link.of(uriTemplate, COLLECTION);
	}

	public String getUrl() {
		return linkTo(UserController.class).toUri().toString();
	}

	public static TemplateVariables buildFilterTemplateVariables() {
		return new TemplateVariables(new TemplateVariable("userType", VariableType.REQUEST_PARAM),
				new TemplateVariable("email", VariableType.REQUEST_PARAM),
				new TemplateVariable("courseId", VariableType.REQUEST_PARAM),
				new TemplateVariable("userStatus", VariableType.REQUEST_PARAM));
	}
	
	public static String buildUriLocation(UUID userId) {
		return fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(userId).toUri().toString();
	}

}
