package com.ead.course.services.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ead.course.assembler.CourseModelAssembler;
import com.ead.course.entity.CourseEntity;
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
	private final CourseModelAssembler mapper;

	@Transactional
	@Override
	public void delete(CourseEntity course) {
		moduleService.deleteAllModulesByCourseId(course.getCourseId());
		repository.delete(course);
	}

	@Override
	public CourseModel save(CourseModel courseModel) {
		CourseEntity courseEntity = mapper.toEntity(courseModel);
		CourseEntity save = repository.save(courseEntity);
		return mapper.toModel(courseEntity);
	}

}
