package com.ead.course.services;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.ead.course.entity.CourseEntity;
import com.ead.course.model.CourseModel;

public interface CourseService {

	public CourseModel save(CourseModel courseModel);

	public void deleteById(UUID courseId);

	public CourseModel updateCourse(UUID courseId, CourseModel courseModel);

	public PagedModel<CourseModel> findAll(Pageable pageable);

	public CourseModel findCourse(UUID courseId);
	
	public CourseEntity findCourseEntity(UUID courseId);

}
