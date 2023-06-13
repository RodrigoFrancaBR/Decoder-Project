package com.ead.course.usecase;

import java.util.UUID;

import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import com.ead.course.assembler.ModuleEntityAssembler;
import com.ead.course.assembler.ModuleModelAssembler;
import com.ead.course.model.ModuleModel;
import com.ead.course.services.CourseService;
import com.ead.course.services.ModuleService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ModuleFacade {
	private final CourseService courseService;
	private final ModuleService moduleService;
	private final ModuleModelAssembler modelAssembler;
	private final ModuleEntityAssembler entityAssembler;

	public ModuleModel saveModule(UUID courseId, ModuleModel moduleModel) {
		var moduleEntity = entityAssembler.toEntity(moduleModel);
		moduleEntity.setCourse(courseService.findCourseEntity(courseId));
		return modelAssembler.toModel(moduleService.save(moduleEntity));
	}

	public ModuleModel updateModule(UUID courseId, UUID moduleId, ModuleModel moduleModel) {
		var moduleEntity = moduleService.findByCourse(courseId, moduleId);
		entityAssembler.copyNonNullProperties(moduleModel, moduleEntity);
		return modelAssembler.toModel(moduleService.save(moduleEntity));
	}

	public void deleteModule(UUID courseId, UUID moduleId) {
		moduleService.delete(moduleService.findByCourse(courseId, moduleId));
	}

	public CollectionModel<ModuleModel> findAllModulesByCourse(UUID courseId) {
		return modelAssembler.toCollectionModel(moduleService.findAllByCourseId(courseId));
	}

	public ModuleModel findOneModuleByCourseId(UUID courseId, UUID moduleId) {
		return modelAssembler.toModel(moduleService.findByCourse(courseId, moduleId));
	}

}
