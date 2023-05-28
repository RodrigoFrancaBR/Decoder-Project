package com.ead.course.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ead.course.entity.LessonEntity;
import com.ead.course.repository.LessonRepository;
import com.ead.course.services.LessonService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

	private final LessonRepository lessonRepository;

	@Override
	public void deleteAllLessonsByModuleId(UUID moduleId) {
		List<LessonEntity> lessons = lessonRepository.findAllLessonsByModuleId(moduleId);
		if (!lessons.isEmpty()) {
			lessonRepository.deleteAll(lessons);
		}
	}

	@Override
	public List<LessonEntity> findAllLessonsByModuleId(UUID moduleId) {
		List<LessonEntity> lessons = lessonRepository.findAllLessonsByModuleId(moduleId);
		return lessons;
	}
}
