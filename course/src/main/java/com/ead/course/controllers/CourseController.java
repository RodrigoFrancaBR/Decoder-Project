package com.ead.course.controllers;

import static org.springframework.http.ResponseEntity.ok;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ead.course.controllers.views.CourseEntryView;
import com.ead.course.controllers.views.CourseReturnView;
import com.ead.course.model.CourseModel;
import com.ead.course.services.CourseService;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/courses")
@RestController
public class CourseController {

	private final CourseService service;

	@JsonView(CourseReturnView.Default.class)
	@PostMapping
	public ResponseEntity<CourseModel> saveCourse(
			@RequestBody
			@Validated(CourseEntryView.RegisterCourse.class)
			@JsonView(CourseEntryView.RegisterCourse.class)			
			CourseModel courseModel) {

		var course = service.save(courseModel);

		return ResponseEntity.status(HttpStatus.CREATED).body(course);
	}

	@DeleteMapping(path = "/{courseId}")	
	public ResponseEntity<String> deleteCourse(
				@PathVariable(value="courseId") 
				UUID courseId){
		service.deleteById(courseId);
		return ok().body("Course deleted successfully");		
	}
	
	@JsonView(CourseReturnView.Default.class)
	@PutMapping(path = "{courseId}")
	public CourseModel updateCourse(
			@PathVariable UUID courseId,
			@RequestBody
			@Validated(CourseEntryView.UpdateCourse.class)
			@JsonView(CourseEntryView.UpdateCourse.class) 
			CourseModel courseModel) {

		return service.updateCourse(courseId, courseModel);
	}


}
