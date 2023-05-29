package com.ead.course.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ead.course.model.CourseModel;
import com.ead.course.services.CourseService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/courses")
@RestController
public class CourseController {

	private final CourseService service;

	@PostMapping
	public ResponseEntity<CourseModel> saveCourse(@RequestBody CourseModel courseModel) {

		CourseModel savedModel = service.save(courseModel);

		return ResponseEntity.status(HttpStatus.CREATED).body(courseModel);
	}

}
