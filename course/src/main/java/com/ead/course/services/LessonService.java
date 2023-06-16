package com.ead.course.services;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ead.course.entity.LessonEntity;
import com.ead.course.entity.ModuleEntity;

public interface LessonService {

	void deleteAll(Set<LessonEntity> lessons);

	void deleteAllByModule(ModuleEntity module);

	void deleteAllByModuleId(UUID moduleId);

	List<LessonEntity> findAllByModuleId(UUID moduleId);

	LessonEntity save(LessonEntity lessonEntity);

	LessonEntity findByModuleAndLessonId(UUID moduleId, UUID lessonId);

	void delete(LessonEntity lessonEntity);

	Page<LessonEntity> findAll(Pageable pageable);

}
