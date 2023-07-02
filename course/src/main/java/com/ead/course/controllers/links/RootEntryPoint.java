package com.ead.course.controllers.links;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class RootEntryPoint {


    private final LinksCourse course;
    private final LinksModule module;
    private final LinksLessons lesson;

    @GetMapping
    public RootEntryPointModel root() {
        var rootEntryPointModel = new RootEntryPointModel();

        return rootEntryPointModel.add(
                course.linkToCourses(),
                module.linkToModules(),
                lesson.linkToLessons());
    }

    private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {

    }
}
