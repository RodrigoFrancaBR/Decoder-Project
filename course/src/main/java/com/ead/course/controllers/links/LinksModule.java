package com.ead.course.controllers.links;

import com.ead.course.controllers.ModuleController;
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
public class LinksModule extends LinksFactory {

    public Link linkToModules() {
        var templateVariables = TEMPLATE_VARIABLES.concat(buildFilterTemplateVariables());

        var uriTemplate = UriTemplate.of(linkToModuleController(), templateVariables);

        return Link.of(uriTemplate, "Modules");
    }

    private TemplateVariables buildFilterTemplateVariables() {
        return new TemplateVariables(
                new TemplateVariable("title", VariableType.REQUEST_PARAM),
                new TemplateVariable("description", VariableType.REQUEST_PARAM));
    }

    private String linkToModuleController() {
        return linkTo(ModuleController.class).toUri().toString();
    }

    public String linkToFindByCourseAndModuleId(UUID courseId, UUID moduleId) {
        return linkTo(methodOn(ModuleController.class)
                .findByCourseAndModuleId(courseId, moduleId))
                .toUri()
                .toString();
    }
}
