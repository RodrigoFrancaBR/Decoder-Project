package com.ead.course.services;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.ead.course.entity.LessonEntity;
import com.ead.course.entity.ModuleEntity;

public interface LessonService {
	
	void deleteAll(Set<LessonEntity> lessons);

	void deleteAllByModule(ModuleEntity module);

	void deleteAllLessonsByModuleId(UUID moduleId);

	List<LessonEntity> findAllLessonsByModuleId(UUID moduleId);

	

}
