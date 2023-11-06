package com.ead.course.controllers;

import com.ead.course.controllers.links.LinksModule;
import com.ead.course.controllers.views.ModuleEntryView;
import com.ead.course.controllers.views.ModuleReturnView;
import com.ead.course.model.ModuleModel;
import com.ead.course.usecase.ModuleFacade;
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
public class ModuleController {

	private final ModuleFacade facade;
	private final LinksModule links;

	@JsonView(ModuleReturnView.Default.class)
	@GetMapping("/courses/{courseId}/modules")
	public PagedModel<ModuleModel> findAllByTitleAndCourseId(
			@PageableDefault(page = 0, size = 10, sort = "moduleId", direction = Sort.Direction.DESC)
			Pageable pageable,
			@PathVariable UUID courseId,
            @RequestParam(required = false) String title){
		return facade.findAllByTitleAndCourseId(title, courseId, pageable);
	}

	@GetMapping("/courses/{courseId}/modules/{moduleId}")
	public ModuleModel findByCourseAndModuleId(
			@PathVariable UUID courseId,
			@PathVariable UUID moduleId){
		return facade.findByCourseAndModuleId(courseId, moduleId);
	}

	@JsonView(ModuleReturnView.Default.class)
	@PostMapping(path = "/courses/{courseId}/modules")
	public ResponseEntity<ModuleModel> save(
			@PathVariable UUID courseId,
			@RequestBody
			@Validated(ModuleEntryView.RegisterModule.class)
			@JsonView(ModuleEntryView.RegisterModule.class)
			ModuleModel moduleModel) {

		var savedModel = facade.saveModule(courseId, moduleModel);
		var location = links.linkToFindByCourseAndModuleId(courseId, savedModel.getModuleId());
		return status(CREATED).header(LOCATION, location).body(savedModel);
	}

	@JsonView(ModuleReturnView.Default.class)
	@PutMapping(path = "/courses/{courseId}/modules/{moduleId}")
	public ResponseEntity<String> updateByCourseId(
			@PathVariable UUID courseId,
			@PathVariable UUID moduleId,
			@RequestBody
			@JsonView(ModuleEntryView.UpdateModule.class)
			ModuleModel moduleModel) {

		facade.updateByCourseId(courseId, moduleId, moduleModel);
		return ok().body("Module updated successfully");
	}

	@DeleteMapping(path = "/courses/{courseId}/modules/{moduleId}")
	public ResponseEntity<String> deleteByCourseId(
			@PathVariable UUID courseId,
			@PathVariable UUID moduleId) {

		facade.deleteByCourseId(courseId, moduleId);
		return ok().body("Module deleted successfully");
	}
}
