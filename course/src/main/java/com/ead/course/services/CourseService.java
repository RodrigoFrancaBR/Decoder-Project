package com.ead.course.services;

import java.util.UUID;

import com.ead.course.model.CourseModel;

public interface CourseService {

	public CourseModel save(CourseModel courseModel);

	public void deleteById(UUID courseId);

	public CourseModel updateCourse(UUID courseId, CourseModel courseModel);
}
