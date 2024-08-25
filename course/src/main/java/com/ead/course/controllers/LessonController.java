package com.ead.course.controllers;

import com.ead.course.controllers.links.LinksLessons;
import com.ead.course.controllers.views.LessonEntryView;
import com.ead.course.controllers.views.LessonReturnView;
import com.ead.course.model.LessonModel;
import com.ead.course.usecase.LessonFacade;
import com.fasterxml.jackson.annotation.JsonInclude;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LessonController {

	private final LessonFacade facade;
	private final LinksLessons links;
	
	@JsonView(LessonReturnView.Default.class)
	@GetMapping(path = "/modules/{moduleId}/lessons")
	public PagedModel<LessonModel> findAllByTitleAndModuleId(
			@PageableDefault(page = 0, size = 10, sort = "lessonId", direction = Sort.Direction.DESC)
			Pageable pageable,
			@PathVariable UUID moduleId,
			@RequestParam(required = false) String title) {

		return facade.findAllByTitleAndModuleId(title, moduleId, pageable);
	}

	@JsonView(LessonReturnView.Default.class)
	@GetMapping(path = "/modules/{moduleId}/lessons/{lessonId}")
	public LessonModel findByModuleAndLessonId(
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
		var savedModel = facade.saveLesson(moduleId, lessonModel);
		var location = links.buildUriLocation(moduleId, savedModel.getLessonId());
		return status(CREATED).header(LOCATION, location).body(savedModel);

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
