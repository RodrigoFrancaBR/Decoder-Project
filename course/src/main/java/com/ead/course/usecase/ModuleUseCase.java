package com.ead.course.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ead.authuser.exceptions.UserNotFoundException;
import com.ead.course.assembler.ModuleModelAssembler;
import com.ead.course.entity.ModuleEntity;
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

	public ModuleModel saveModule(UUID courseId, ModuleModel moduleModelRequest) {

		var findCourseEntity = courseService.findCourseEntity(courseId);
		ModuleEntity moduleEntity = assembler.toEntity(moduleModelRequest);

		moduleEntity.setCourse(findCourseEntity);

		ModuleEntity saveEntity = moduleService.save(moduleEntity);
		ModuleModel moduleModelResponse = assembler.toModel(saveEntity);

		return moduleModelResponse;
	}

	public void deleteModule(UUID courseId, UUID moduleId) {
		var moduleEntity = moduleService.findModuleByCourse(courseId, moduleId);
		moduleService.delete(moduleEntity);		
	}

}
