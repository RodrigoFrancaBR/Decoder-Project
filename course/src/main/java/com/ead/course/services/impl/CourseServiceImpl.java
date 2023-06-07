package com.ead.course.services.impl;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import com.ead.course.assembler.CourseModelAssembler;
import com.ead.course.entity.CourseEntity;
import com.ead.course.exceptions.CourseNotFoundException;
import com.ead.course.model.CourseModel;
import com.ead.course.repository.CourseRepository;
import com.ead.course.services.CourseService;
import com.ead.course.services.ModuleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

	private final ModuleService moduleService;
	private final CourseRepository repository;
	private final CourseModelAssembler assembler;
	private final PagedResourcesAssembler<CourseEntity> pagedResourcesAssembler;

	@Override
	public CourseModel save(CourseModel courseModel) {
		CourseEntity courseEntity = assembler.toEntity(courseModel);
		CourseEntity save = repository.save(courseEntity);
		return assembler.toModel(courseEntity);
	}

	@Override
	public PagedModel<CourseModel> findAll(Pageable pageable) {
		Page<CourseEntity> pageCourseEntity = repository.findAll(pageable);
		PagedModel<CourseModel> model = pagedResourcesAssembler.toModel(pageCourseEntity, assembler);
		return model;
	}

	@Transactional
	private void delete(CourseEntity course) {
		moduleService.deleteAllModulesByCourseId(course.getCourseId());
		repository.delete(course);
	}

	@Override
	public void deleteById(UUID courseId) {
		var course = findCourseIfExist(courseId);
		delete(course);
	}

	@Override
	public CourseModel updateCourse(UUID courseId, CourseModel courseModel) {
		var courseEntity = findCourseIfExist(courseId);
		CourseEntity entityMapped = assembler.toEntity(courseModel);
		assembler.copyPropertiesCannotBeModified(courseEntity, entityMapped);

		var saveEntity = repository.save(entityMapped);

		var saveModel = assembler.toModel(saveEntity);

		return saveModel;
	}

	public CourseEntity findCourseIfExist(UUID courseId) {
		return repository.findById(courseId).orElseThrow(() -> new CourseNotFoundException("Course not found"));
	}

	@Override
	public CourseModel findCourse(UUID courseId) {
		return assembler.toModel(findCourseIfExist(courseId));
	}

	@Override
	public CourseEntity findCourseEntity(UUID courseId) {
		return findCourseIfExist(courseId);
	}
}
