package com.ead.course.services;

import com.ead.course.entity.CourseEntity;
import com.ead.course.model.CourseModel;

public interface CourseService {

	public void delete(CourseEntity course);

	public CourseModel save(CourseModel courseModel);
}
