package com.ead.course.usecase;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import com.ead.course.assembler.CourseEntityAssembler;
import com.ead.course.assembler.CourseModelAssembler;
import com.ead.course.entity.CourseEntity;
import com.ead.course.model.CourseModel;
import com.ead.course.repository.CourseRepository;
import com.ead.course.services.CourseService;
import com.ead.course.services.LessonService;
import com.ead.course.services.ModuleService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CourseFacade {

	private final CourseService courseService;
	private final ModuleService moduleService;
	private final LessonService lessonService;

	private final CourseModelAssembler modelAssembler;
	private final CourseEntityAssembler entityAssembler;

	private final PagedResourcesAssembler<CourseEntity> pagedResourcesAssembler;

	public PagedModel<CourseModel> findAllCourse(Pageable pageable) {
		return pagedResourcesAssembler.toModel(courseService.findAll(pageable), modelAssembler);
	}

	public CourseModel findCourse(UUID courseId) {
		return modelAssembler.toModel(courseService.findByCourseId(courseId));
	}

	public CourseModel saveCourse(CourseModel courseModel) {
		var saveEntity = courseService.save(entityAssembler.toEntity(courseModel));
		return modelAssembler.toModel(saveEntity);
	}

	public CourseModel updateCourse(UUID courseId, UUID moduleId, CourseModel courseModel) {
		var courseEntity = courseService.findByCourseId(courseId);
		entityAssembler.copyNonNullProperties(courseModel, courseEntity);
		return modelAssembler.toModel(courseService.save(courseEntity));
	}

	@Transactional
	public void deleteCourse(UUID courseId) {
		var courseEntity = courseService.findByCourseId(courseId);
		var moduleEntityList = moduleService.findAllByCourseId(courseEntity.getCourseId());
		moduleEntityList.parallelStream().forEach(module -> lessonService.deleteAll(module.getLessons()));
		moduleService.deleteAll(moduleEntityList);
		courseService.deleteById(courseEntity.getCourseId());
	}

}
