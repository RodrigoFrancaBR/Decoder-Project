package com.ead.authuser.controllers;

import com.ead.authuser.clienthttp.CourseClient;
import com.ead.authuser.controllers.views.UserEntryView;
import com.ead.authuser.controllers.views.UserReturnView;
import com.ead.authuser.model.ResponseModel;
import com.ead.authuser.model.UserModel;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserCourseController {
    private final CourseClient client;

    @JsonView(UserReturnView.Default.class)
    @GetMapping("/users/{userId}/courses")
    public ResponseEntity<ResponseModel> getAllCoursesByUser(
        @PageableDefault(page = 0, size = 10, sort = "courseId", direction = Sort.Direction.ASC) Pageable pageable,
        @PathVariable UUID userId,
        @JsonView(UserEntryView.FilterUser.class) UserModel userModel) {
        return client.getAllCoursesByUser(userId, pageable);
    }

}
