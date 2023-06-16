package com.ead.course.services.impl;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ead.course.entity.LessonEntity;
import com.ead.course.entity.ModuleEntity;
import com.ead.course.exceptions.LessonNotFoundException;
import com.ead.course.repository.LessonRepository;
import com.ead.course.services.LessonService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

	private final LessonRepository repository;

	@Override
	public Page<LessonEntity> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	@Transactional
	@Override
	public void deleteAll(Set<LessonEntity> lessons) {
		repository.deleteAll(lessons);
	}

	@Override
	public void deleteAllByModule(ModuleEntity module) {
		repository.deleteAll(module.getLessons());
	}

	@Override
	public void deleteAllByModuleId(UUID moduleId) {
		List<LessonEntity> lessons = repository.findAllByModuleId(moduleId);
		if (!lessons.isEmpty()) {
			repository.deleteAll(lessons);
		}
	}

	@Override
	public List<LessonEntity> findAllByModuleId(UUID moduleId) {
		List<LessonEntity> lessons = repository.findAllByModuleId(moduleId);
		return lessons;
	}

	@Override
	public LessonEntity save(LessonEntity lessonEntity) {
		return repository.save(lessonEntity);
	}

	@Override
	public LessonEntity findByModuleAndLessonId(UUID moduleId, UUID lessonId) {
		return findByModuleAndLessonIdIfExist(moduleId, lessonId);
	}

	private LessonEntity findByModuleAndLessonIdIfExist(UUID moduleId, UUID lessonId) {
		return repository.findByModuleAndLessonId(moduleId, lessonId)
				.orElseThrow(() -> new LessonNotFoundException("Lesson not found"));
	}

	@Override
	public void delete(LessonEntity lessonEntity) {
		repository.delete(lessonEntity);
	}

}
