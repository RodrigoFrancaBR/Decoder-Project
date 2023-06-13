package com.ead.course.services.impl;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.stereotype.Service;

import com.ead.course.assembler.CourseEntityAssembler;
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
	private final CourseEntityAssembler entityAssembler;
	private final CourseModelAssembler modelAssembler;
	private final PagedResourcesAssembler<CourseEntity> pagedResourcesAssembler;
	private final CourseRepository repository;

	@Override
	public Page<CourseEntity> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public CourseEntity findByCourseId(UUID courseId) {
		return findByCourseIdIfExist(courseId);
	}

	private CourseEntity findByCourseIdIfExist(UUID courseId) {
		return repository.findById(courseId).orElseThrow(() -> new CourseNotFoundException("Course not found"));
	}

	@Override
	public CourseEntity save(CourseEntity courseEntity) {
		return repository.save(courseEntity);
	}

	@Override
	public void deleteById(UUID courseId) {
		delete(findByCourseIdIfExist(courseId));
	}

	@Override
	public CourseModel findCourse(UUID courseId) {
		return modelAssembler.toModel(findByCourseIdIfExist(courseId));
	}

	@Override
	public CourseModel updateCourse(UUID courseId, CourseModel courseModel) {
		var courseEntity = findByCourseIdIfExist(courseId);
		entityAssembler.copyNonNullProperties(courseModel, courseEntity);
		return modelAssembler.toModel(repository.save(courseEntity));
	}

	@Transactional
	private void delete(CourseEntity course) {
		moduleService.deleteAllByCourse(course.getCourseId());
		repository.delete(course);
	}

	@Override
	public CourseEntity findCourseEntity(UUID courseId) {
		return findByCourseIdIfExist(courseId);
	}

	@Override
	public CourseEntity findByCourse(CourseEntity course) {
		return findByCourseIdIfExist(course.getCourseId());
	}

	@Override
	public CourseModel save(CourseModel courseModel) {
		var courseEntity = entityAssembler.toEntity(courseModel);
		return modelAssembler.toModel(courseEntity);
	}

}
