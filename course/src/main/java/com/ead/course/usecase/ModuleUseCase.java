package com.ead.course.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ead.course.assembler.ModuleModelAssembler;
import com.ead.course.model.ModuleModel;
import com.ead.course.services.CourseService;
import com.ead.course.services.ModuleService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ModuleUseCase {
	private final CourseService courseService;
	private final ModuleService moduleService;
	private final ModuleModelAssembler assembler;

	public ModuleModel saveModule(UUID courseId, ModuleModel moduleModel) {
		var course = courseService.findCourseEntity(courseId);
		var moduleEntity = assembler.toEntity(moduleModel);
		moduleEntity.setCourse(course);
		var saveEntity = moduleService.save(moduleEntity);
		var saveModel = assembler.toModel(saveEntity);

		return saveModel;
	}

	public void deleteModule(UUID courseId, UUID moduleId) {
		var moduleEntity = moduleService.findModuleByCourse(courseId, moduleId);
		moduleService.delete(moduleEntity);
	}

	public ModuleModel updateModule(UUID courseId, UUID moduleId, ModuleModel moduleModel) {
		var moduleEntity = moduleService.findModuleByCourse(courseId, moduleId);
		ModuleModelAssembler.copyNonNullProperties(moduleModel, moduleEntity);
		var moduleEntitySave = moduleService.save(moduleEntity);
		var moduleModelSave = assembler.toModel(moduleEntitySave);
		return moduleModelSave;
	}

}
