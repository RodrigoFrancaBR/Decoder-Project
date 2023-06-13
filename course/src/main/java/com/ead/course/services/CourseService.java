package com.ead.course.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ead.course.entity.CourseEntity;
import com.ead.course.model.CourseModel;

public interface CourseService {

	public Page<CourseEntity> findAll(Pageable pageable);

	public CourseEntity findByCourseId(UUID courseId);
	
	public CourseEntity save(CourseEntity courseEntity);

	public void deleteById(UUID courseId);
	
	public CourseEntity findCourseEntity(UUID courseId);

	// public PagedModel<CourseModel> findAll(Pageable pageable);

	

	public CourseModel updateCourse(UUID courseId, CourseModel courseModel);

	public CourseModel findCourse(UUID courseId);

	

	public CourseEntity findByCourse(CourseEntity course);

	public CourseModel save(CourseModel courseModel);

}
