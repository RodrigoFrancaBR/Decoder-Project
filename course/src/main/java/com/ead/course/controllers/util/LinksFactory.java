package com.ead.course.controllers.util;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.UUID;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.stereotype.Component;

import com.ead.course.controllers.CourseController;

@Component
public class LinksFactory {

	private static final TemplateVariables TEMPLATE_VARIABLES = new TemplateVariables(
			new TemplateVariable("page", VariableType.REQUEST_PARAM),
			new TemplateVariable("size", VariableType.REQUEST_PARAM),
			new TemplateVariable("sort", VariableType.REQUEST_PARAM),
			new TemplateVariable("direction", VariableType.REQUEST_PARAM));

	public Link linkToCourses() {
		var templateVariables = TEMPLATE_VARIABLES.concat(buildFilterTemplateVariables());
		
		var uriTemplate = UriTemplate.of(getUrl(), templateVariables);
		
		return Link.of(uriTemplate, "Courses");
	}

	private String getUrl() {
		return linkTo(CourseController.class).toUri().toString();
	}

	private TemplateVariables buildFilterTemplateVariables() {
		return new TemplateVariables(new TemplateVariable("userType", VariableType.REQUEST_PARAM),
				new TemplateVariable("email", VariableType.REQUEST_PARAM),
				new TemplateVariable("courseId", VariableType.REQUEST_PARAM),
				new TemplateVariable("userStatus", VariableType.REQUEST_PARAM));
	}

	public String buildUriLocation(UUID courseId) {
		return linkTo(methodOn(CourseController.class)
				.getOneCourse(courseId)).toUri().toString();
	}

}
