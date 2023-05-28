package com.ead.course.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ead.course.entity.ModuleEntity;
import com.ead.course.repository.ModuleRepository;
import com.ead.course.services.LessonService;
import com.ead.course.services.ModuleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModuleServiceImpl implements ModuleService {
	
	private final ModuleRepository moduleRepository;
	private final LessonService lessonService;

	@Override
	public void deleteAllModulesByCourseId(UUID courseId) {
		List<ModuleEntity> modules = moduleRepository.findAllModulesByCourse(courseId);
		deleteAllModulesIfExist(modules);
	}

	private void deleteAllModulesIfExist(List<ModuleEntity> modules) {
		if (!modules.isEmpty()) {
			for (ModuleEntity module : modules) {
				lessonService.deleteAllLessonsByModuleId(module.getModuleId());
			}
			moduleRepository.deleteAll(modules);
		}
	}
}
