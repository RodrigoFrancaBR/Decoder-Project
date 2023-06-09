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
import org.springframework.web.bind.annotation.RestController;

import com.ead.authuser.controllers.views.UserEntryView;
import com.ead.authuser.controllers.views.UserReturnView;
import com.ead.authuser.model.UserModel;
import com.ead.course.controllers.views.ModuleEntryView;
import com.ead.course.controllers.views.ModuleReturnView;
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

	@JsonView(ModuleReturnView.Default.class)
	@PostMapping(path = "/courses/{courseId}/modules")
	public ResponseEntity<ModuleModel> saveModule(
			@PathVariable(value = "courseId")UUID courseId,
			@RequestBody
			@Validated(ModuleEntryView.RegisterModule.class)
			@JsonView(ModuleEntryView.RegisterModule.class)
			ModuleModel moduleModel) {
			ModuleModel saveModule = useCase.saveModule(courseId, moduleModel);

		return ResponseEntity.status(HttpStatus.CREATED).body(saveModule);

	}
	
	@DeleteMapping(path = "/courses/{courseId}/modules/{moduleId}")
	public ResponseEntity<String> deleteModule(
			@PathVariable UUID courseId,
			@PathVariable UUID moduleId) {

		useCase.deleteModule(courseId, moduleId);
		return ok().body("Module deleted successfully");
	}
	
	// @JsonView(ModuleReturnView.Default.class)
	@PutMapping(path = "/courses/{courseId}/modules/{moduleId}")
	public ResponseEntity<String> updateModule(
			@PathVariable UUID courseId,
			@PathVariable UUID moduleId,
			@RequestBody
			@JsonView(ModuleEntryView.UpdateModule.class) 
			ModuleModel moduleModel) {

		useCase.updateModule(courseId, moduleId,moduleModel);
		return ok().body("Module updated successfully");
	}

	public Class<?> getOneModule(UUID moduleId) {
		// TODO Auto-generated method stub
		return null;
	}

}
