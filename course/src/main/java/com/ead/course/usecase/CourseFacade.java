package com.ead.course.usecase;

import org.springframework.stereotype.Service;

import com.ead.course.assembler.CourseEntityAssembler;
import com.ead.course.assembler.CourseModelAssembler;
import com.ead.course.model.CourseModel;
import com.ead.course.services.CourseService;
import com.ead.course.services.ModuleService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CourseFacade {
	private final CourseService courseService;
	private final ModuleService moduleService;
	private final CourseModelAssembler modelAssembler;
	private final CourseEntityAssembler entityAssembler;

	public CourseModel saveCourse(CourseModel courseModel) {
		var courseEntity = entityAssembler.toEntity(courseModel);
		var saveEntity = courseService.save(courseEntity);
		return modelAssembler.toModel(saveEntity);
	}
/*
	public CourseModel updateModule(UUID courseId, UUID moduleId, CourseModel moduleModel) {
		var moduleEntity = moduleService.findByCourse(courseId, moduleId);
		entityAssembler.copyNonNullProperties(moduleModel, moduleEntity);
		return modelAssembler.toModel(moduleService.save(moduleEntity));
	}

	public void deleteModule(UUID courseId, UUID moduleId) {
		moduleService.delete(moduleService.findByCourse(courseId, moduleId));
	}

	public CollectionModel<CourseModel> findAllModulesByCourse(UUID courseId) {
		return modelAssembler.toCollectionModel(moduleService.findAllByCourse(courseId));
	}

	public CourseModel findOneModuleByCourseId(UUID courseId, UUID moduleId) {
		return modelAssembler.toModel(moduleService.findByCourse(courseId, moduleId));
	}
*/
}
