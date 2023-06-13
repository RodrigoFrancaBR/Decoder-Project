package com.ead.course.services.impl;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ead.course.entity.LessonEntity;
import com.ead.course.entity.ModuleEntity;
import com.ead.course.repository.LessonRepository;
import com.ead.course.services.LessonService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

	private final LessonRepository repository;

	@Override
	public void deleteAll(Set<LessonEntity> lessons) {
		repository.deleteAll(lessons);
	}

	@Override
	public void deleteAllByModule(ModuleEntity module) {
		repository.deleteAll(module.getLessons());
	}

	@Override
	public void deleteAllLessonsByModuleId(UUID moduleId) {
		List<LessonEntity> lessons = repository.findAllLessonsByModuleId(moduleId);
		if (!lessons.isEmpty()) {
			repository.deleteAll(lessons);
		}
	}

	@Override
	public List<LessonEntity> findAllLessonsByModuleId(UUID moduleId) {
		List<LessonEntity> lessons = repository.findAllLessonsByModuleId(moduleId);
		return lessons;
	}

	@Override
	public LessonEntity save(LessonEntity lessonEntity) {
		return repository.save(lessonEntity);
	}

}
