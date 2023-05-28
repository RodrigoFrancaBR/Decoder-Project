package com.ead.course.services.impl;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ead.course.entity.CourseEntity;
import com.ead.course.entity.LessonEntity;
import com.ead.course.entity.ModuleEntity;
import com.ead.course.repository.CourseRepository;
import com.ead.course.repository.LessonRepository;
import com.ead.course.repository.ModuleRepository;
import com.ead.course.services.CourseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {	
	private final CourseRepository courseRepository;
	private final ModuleRepository moduleRepository;
	private final LessonRepository lessonRepository;

	@Transactional
	@Override
	public void delete(CourseEntity course) {
		List<ModuleEntity> modules = moduleRepository.findAllModulesByCourse(course.getCourseId());

		deleteAllModulesIfExist(modules);

		courseRepository.delete(course);
	}

	private void deleteAllModulesIfExist(List<ModuleEntity> modules) {
		if (!modules.isEmpty()) {
			for (ModuleEntity module : modules) {
				deleteAllLessonsIfExist(module.getModuleId());
			}
			moduleRepository.deleteAll(modules);
		}
	}

	private void deleteAllLessonsIfExist(UUID moduleId) {
		List<LessonEntity> lessons = lessonRepository.findAllLessonsByModule(moduleId);
		if (!lessons.isEmpty()) {
			lessonRepository.deleteAll(lessons);
		}
	}

}
