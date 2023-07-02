package com.ead.course.controllers.links;

import com.ead.course.controllers.CourseController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class LinksCourse extends LinksFactory {

    public Link linkToCourses() {
        var templateVariables = TEMPLATE_VARIABLES.concat(buildFilterTemplateVariables());

        var uriTemplate = UriTemplate.of(getUrl(), templateVariables);

        return Link.of(uriTemplate, "Courses");
    }

    private TemplateVariables buildFilterTemplateVariables() {
        return new TemplateVariables(new TemplateVariable("title", VariableType.REQUEST_PARAM),
                new TemplateVariable("description", VariableType.REQUEST_PARAM));
    }

    private String getUrl() {
        return linkTo(CourseController.class).toUri().toString();
    }

    public String buildUriLocation(UUID courseId) {
        return linkTo(methodOn(CourseController.class)
                .find(courseId))
                .toUri().toString();
    }

}
