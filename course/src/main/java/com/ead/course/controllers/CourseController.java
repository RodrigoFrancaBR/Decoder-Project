package com.ead.course.controllers;

import com.ead.course.controllers.links.LinksCourse;
import com.ead.course.controllers.views.CourseEntryView;
import com.ead.course.controllers.views.CourseReturnView;
import com.ead.course.model.CourseModel;
import com.ead.course.usecase.CourseFacade;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/courses")
@RestController
public class CourseController {

    private final CourseFacade facade;
    private final LinksCourse links;

    @JsonView(CourseReturnView.Default.class)
    @GetMapping
    public PagedModel<CourseModel> findAll(
        @PageableDefault(page = 0, size = 10, sort = "courseId", direction = Sort.Direction.ASC) Pageable pageable,
        @JsonView(CourseEntryView.FilterCourse.class) CourseModel courseModel) {
        return facade.findAllCourse(pageable, courseModel);
    }

    // receber requestParam ao invés do objeto CourseModel
    // ver como funciona o uso do JPA Search using Spring Data Example
    @JsonView(CourseReturnView.Default.class)
    @GetMapping(path = "byLevelAndStatusAndName")
    public PagedModel<CourseModel> findAllByLevelAndStatusAndName(
        @PageableDefault(page = 0, size = 10, sort = "courseId", direction = Sort.Direction.DESC)
        Pageable pageable,
        @JsonView(CourseEntryView.FilterByLevelAndStatusAndName.class)
        @Validated(CourseEntryView.FilterByLevelAndStatusAndName.class)
        CourseModel courseModel
    ) {
        return facade.findAllByLevelAndStatusAndName(pageable, courseModel);
    }


    @JsonView(CourseReturnView.Default.class)
    @GetMapping(path = "/{courseId}")
    public CourseModel find(@PathVariable UUID courseId) {
        return facade.findCourse(courseId);
    }

    @JsonView(CourseReturnView.Default.class)
    @PostMapping
    public ResponseEntity<CourseModel> save(
        @RequestBody
        @JsonView(CourseEntryView.RegisterCourse.class)
        @Validated(CourseEntryView.RegisterCourse.class)
        CourseModel courseModel) {

        var savedModel = facade.saveCourse(courseModel);
        var location = links.buildUriLocation(savedModel.getCourseId());
        return status(CREATED).header(LOCATION, location).body(savedModel);
    }

    @JsonView(CourseReturnView.Default.class)
    @PutMapping(path = "{courseId}")
    public CourseModel update(
        @PathVariable UUID courseId,
        @RequestBody
        @Validated(CourseEntryView.UpdateCourse.class)
        @JsonView(CourseEntryView.UpdateCourse.class)
        CourseModel courseModel) {

        return facade.updateCourse(courseId, courseModel);
    }

    @DeleteMapping(path = "/{courseId}")
    public ResponseEntity<String> delete(
        @PathVariable(value = "courseId") UUID courseId) {
        facade.deleteCourse(courseId);
        return ok().body("Course deleted successfully");
    }

}
