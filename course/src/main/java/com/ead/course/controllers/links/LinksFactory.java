package com.ead.course.controllers.links;

import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.stereotype.Component;

@Component
public class LinksFactory {

    protected static final TemplateVariables TEMPLATE_VARIABLES = new TemplateVariables(
            new TemplateVariable("page", VariableType.REQUEST_PARAM),
            new TemplateVariable("size", VariableType.REQUEST_PARAM),
            new TemplateVariable("sort", VariableType.REQUEST_PARAM),
            new TemplateVariable("direction", VariableType.REQUEST_PARAM));
}
