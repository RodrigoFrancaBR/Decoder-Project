package com.ead.course.controllers;

import com.ead.course.clienthttp.UserClient;
import com.ead.course.model.UserModel;
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

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    private final UserClient client;

    @GetMapping("/courses/{courseId}/users")
    public PagedModel<UserModel> getAllUsersByCourse(
        @PageableDefault(page = 0, size = 10, sort = "userId", direction = Sort.Direction.ASC) Pageable pageable,
        @PathVariable UUID courseId) {
        return client.getAllUsersByCourse(courseId, pageable);
    }
}