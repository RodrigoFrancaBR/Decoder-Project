package com.ead.course.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ead.course.controllers.views.ModuleEntryView;
import com.ead.course.controllers.views.ModuleReturnView;
import com.ead.course.model.LessonModel;
import com.ead.course.usecase.LessonFacade;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class LessonController {

	private final LessonFacade facade;

	@JsonView(ModuleReturnView.Default.class)
	@PostMapping(path = "/modules/{moduleId}/lessons")
	public ResponseEntity<LessonModel> save(
			@PathVariable(value = "moduleId") UUID moduleId,
			@RequestBody
			@Validated(ModuleEntryView.RegisterModule.class) 
			@JsonView(ModuleEntryView.RegisterModule.class) 
			LessonModel lessonModel) {

		return ResponseEntity.status(HttpStatus.CREATED).body(facade.saveLesson(moduleId, lessonModel));

	}
	/*
	 * @PostMapping("/modules/{moduleId}/lessons") public ResponseEntity<Object>
	 * saveLesson(@PathVariable(value="moduleId") UUID moduleId,
	 * 
	 * @RequestBody @Valid LessonDto lessonDto){
	 * log.debug("POST saveLesson lessonDto received {} ", lessonDto.toString());
	 * Optional<ModuleModel> moduleModelOptional = moduleService.findById(moduleId);
	 * if(!moduleModelOptional.isPresent()){ return
	 * ResponseEntity.status(HttpStatus.NOT_FOUND).body("Module Not Found."); } var
	 * lessonModel = new LessonModel(); BeanUtils.copyProperties(lessonDto,
	 * lessonModel);
	 * lessonModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
	 * lessonModel.setModule(moduleModelOptional.get());
	 * lessonService.save(lessonModel);
	 * log.debug("POST saveLesson lessonId saved {} ", lessonModel.getLessonId());
	 * log.info("Lesson saved successfully lessonId {} ",
	 * lessonModel.getLessonId()); return
	 * ResponseEntity.status(HttpStatus.CREATED).body(lessonModel); }
	 */

}
