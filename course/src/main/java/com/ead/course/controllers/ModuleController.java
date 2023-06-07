package com.ead.course.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ead.course.controllers.views.ModuleEntryView;
import com.ead.course.model.ModuleModel;
import com.ead.course.usecase.ModuleUseCase;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ModuleController {

	private final ModuleUseCase useCase;

	// @JsonView(ModuleReturnView.Default.class)
	@PostMapping("/courses/{courseId}/modules")
	public ResponseEntity<ModuleModel> saveModule(
			@PathVariable(value = "courseId")UUID courseId,
			@RequestBody
			@Validated(ModuleEntryView.RegisterModule.class)
			@JsonView(ModuleEntryView.RegisterModule.class)
			ModuleModel moduleModel) {
			ModuleModel saveModule = useCase.saveModule(courseId, moduleModel);
		// var module = service.save(moduleModel);

		return ResponseEntity.status(HttpStatus.CREATED).body(saveModule);

	}

}