package com.ead.course.services;

import java.util.List;
import java.util.UUID;

import com.ead.course.entity.LessonEntity;

public interface LessonService {

	void deleteAllLessonsByModuleId(UUID moduleId);

	List<LessonEntity>findAllLessonsByModuleId(UUID moduleId);

}
