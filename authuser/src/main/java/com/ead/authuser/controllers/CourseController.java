package com.ead.authuser.controllers;

import com.ead.authuser.clienthttp.CourseClient;
import com.ead.authuser.model.CourseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class CourseController {

    private final CourseClient client;

    @GetMapping("/users/{userId}/courses")
    public PagedModel<CourseModel> getAllCoursesByUser(
        @PageableDefault(page = 0, size = 10, sort = "courseId", direction = Sort.Direction.ASC) Pageable pageable,
        @PathVariable UUID userId) {
        return client.getAllCoursesByUser(userId, pageable);
    }
}