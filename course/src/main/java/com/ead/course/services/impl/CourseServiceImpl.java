package com.ead.course.services.impl;

import java.beans.Beans;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
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
	private final CourseModelAssembler mapper;

	@Transactional	
	private void delete(CourseEntity course) {
		moduleService.deleteAllModulesByCourseId(course.getCourseId());
		repository.delete(course);
	}

	@Override
	public CourseModel save(CourseModel courseModel) {
		CourseEntity courseEntity = mapper.toEntity(courseModel);
		CourseEntity save = repository.save(courseEntity);
		return mapper.toModel(courseEntity);
	}

	@Override
	public void deleteById(UUID courseId) {
		var course = findCourseIfExist(courseId);
		delete(course);		
	}


	@Override
	public CourseModel updateCourse(UUID courseId, CourseModel courseModel) {
		var entity = findCourseIfExist(courseId);
		BeanUtils.copyProperties(courseModel, entity);
		var saveEntity = repository.save(entity);
		var saveModel = mapper.toModel(saveEntity);
		
		return saveModel;
	}
		
	public CourseEntity findCourseIfExist(UUID courseId) {
		return repository.findById(courseId)
				.orElseThrow(() -> new CourseNotFoundException("Course not found"));
	}

}
