package com.ead.course.controllers;

import com.ead.course.controllers.views.LessonEntryView;
import com.ead.course.controllers.views.LessonReturnView;
import com.ead.course.model.LessonModel;
import com.ead.course.usecase.LessonFacade;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LessonController {

	private final LessonFacade facade;
	
	@JsonView(LessonReturnView.Default.class)
	@GetMapping(path = "/modules/{moduleId}/lessons")
	public  ResponseEntity<CollectionModel<LessonModel>> findAll(
			@PathVariable UUID moduleId) {
		var lessonModelList = facade.findAllByModuleId(moduleId);
		return ResponseEntity.status(HttpStatus.OK).body(lessonModelList); 
	}

	@JsonView(LessonReturnView.Default.class)
	@GetMapping(path = "/modules/{moduleId}/lessons/{lessonId}")
	public LessonModel find(
			@PathVariable UUID moduleId,
			@PathVariable UUID lessonId) {
		return facade.findLesson(moduleId, lessonId);		
	}

	@JsonView(LessonReturnView.Default.class)
	@PostMapping(path = "/modules/{moduleId}/lessons")
	public ResponseEntity<LessonModel> save(
			@PathVariable UUID moduleId,
			@RequestBody
			@Validated(LessonEntryView.RegisterLesson.class) 
			@JsonView(LessonEntryView.RegisterLesson.class) 
			LessonModel lessonModel) {

		return ResponseEntity.status(HttpStatus.CREATED).body(facade.saveLesson(moduleId, lessonModel));

	}
	
	@DeleteMapping(path = "/modules/{moduleId}/lessons/{lessonId}")
	public ResponseEntity<String> delete(
				@PathVariable UUID moduleId,
				@PathVariable UUID lessonId) {

		facade.deleteLesson(moduleId, lessonId);
		return ok().body("Lesson deleted successfully");
	}
	
	@JsonView(LessonReturnView.Default.class)
	@PutMapping(path = "/modules/{moduleId}/lessons/{lessonId}")
	public LessonModel update(
			@PathVariable UUID moduleId,
			@PathVariable UUID lessonId,
			@RequestBody
			@JsonView(LessonEntryView.UpdateLesson.class)
			LessonModel lessonModel) {

		return facade.updateLesson(moduleId, lessonId, lessonModel);
	}

}
