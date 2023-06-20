package com.ead.course.controllers;

import com.ead.course.controllers.views.ModuleEntryView;
import com.ead.course.controllers.views.ModuleReturnView;
import com.ead.course.model.ModuleModel;
import com.ead.course.usecase.ModuleFacade;
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
public class ModuleController {

	private final ModuleFacade facade;

	@JsonView(ModuleReturnView.Default.class)
	@PostMapping(path = "/courses/{courseId}/modules")
	public ResponseEntity<ModuleModel> save(
			@PathVariable UUID courseId,
			@RequestBody
			@Validated(ModuleEntryView.RegisterModule.class) 
			@JsonView(ModuleEntryView.RegisterModule.class) 
			ModuleModel moduleModel) {
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(facade.saveModule(courseId, moduleModel));
	}

	@DeleteMapping(path = "/courses/{courseId}/modules/{moduleId}")
	public ResponseEntity<String> delete(
				@PathVariable UUID courseId,
				@PathVariable UUID moduleId) {

		facade.deleteModule(courseId, moduleId);
		return ok().body("Module deleted successfully");
	}

	@JsonView(ModuleReturnView.Default.class)
	@PutMapping(path = "/courses/{courseId}/modules/{moduleId}")
	public ResponseEntity<String> update(
			@PathVariable UUID courseId,
			@PathVariable UUID moduleId,
			@RequestBody
			@JsonView(ModuleEntryView.UpdateModule.class) 
			ModuleModel moduleModel) {

		facade.updateModule(courseId, moduleId, moduleModel);
		return ok().body("Module updated successfully");
	}
		
    @GetMapping("/courses/{courseId}/modules")
    public ResponseEntity<CollectionModel<ModuleModel>> findAll(
    		@PathVariable UUID courseId){
    	var modulesModelList = facade.findAllModulesByCourseId(courseId); 
    	return ResponseEntity.status(HttpStatus.OK).body(modulesModelList);         
    }
    
    @GetMapping("/courses/{courseId}/modules/{moduleId}")
    public ModuleModel find(
    		@PathVariable UUID courseId,
    		@PathVariable UUID moduleId){
		var moduleModel = facade.findOneModuleByCourseId(courseId, moduleId);		
    	return moduleModel;
    }


}
